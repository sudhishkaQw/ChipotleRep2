package com.example.chipotlerep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.chipotlerep.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), HomeFragment.OnMenuItemClickListener {

    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private lateinit var horizontalPagerAdapter: PagerAdapter
    private lateinit var swipeDownAdapter: SwipeDownAdapter
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.horizontalViewPager

        // Initialize horizontal navigation adapter
        horizontalPagerAdapter = PagerAdapter(this)

        // Initialize swipe-down replacement adapter
        swipeDownAdapter = SwipeDownAdapter(this)

        // Set the adapter for horizontal navigation
        viewPager.adapter = horizontalPagerAdapter

        // Set the current item to HomeFragment
        viewPager.setCurrentItem(1, false)

        // Set offscreen page limit to ensure fragments are loaded
        viewPager.offscreenPageLimit = 2 // Set to number of fragments - 1

        // Initialize GestureDetector for swipe actions
        gestureDetector = GestureDetector(this, SwipeGestureListener())

    }

    // PagerAdapter class for horizontal navigation
    inner class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3 // Number of fragments

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SignInFragment()
                1 -> HomeFragment()
                2 -> CartFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }

    // SwipeDownAdapter class for replacing HomeFragment with OrderFragment
    inner class SwipeDownAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 1 // Only one fragment for swipe-down replacement

        override fun createFragment(position: Int): Fragment {
            return OrderFragment()
        }
    }

    // GestureDetector.SimpleOnGestureListener implementation
    inner class SwipeGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val deltaY = e2.y - (e1?.y ?: 0F)

            val currentItem = viewPager.currentItem

            // If swipe down and on HomeFragment, navigate to OrderFragment
            if (deltaY > 0 && currentItem == 1 && Math.abs(deltaY) > SWIPE_THRESHOLD) {
                viewPager.adapter = swipeDownAdapter
                return true
            }
            // If swipe up and on OrderFragment, navigate back to HomeFragment
            if (deltaY < 0 && currentItem != 1 && Math.abs(deltaY) > SWIPE_THRESHOLD) {
                viewPager.adapter = horizontalPagerAdapter
                viewPager.setCurrentItem(1, true) // Change to false if you don't want smooth scroll
                return true
            }

            return false
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return super.onSingleTapUp(e)
        }
    }

    // Override dispatchTouchEvent to pass touch events to GestureDetector
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            gestureDetector.onTouchEvent(ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    // Implementing OnSwipeListener interface methods


    companion object {
        const val SWIPE_THRESHOLD = 100 // Adjust this value as needed
    }

    override fun onMenuItemClick(menuItem: String) {
        // Start the AnotherActivity and pass the selected menu item
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("menu_item", menuItem)
        startActivity(intent)
    }
}