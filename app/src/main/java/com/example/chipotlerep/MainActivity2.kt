package com.example.chipotlerep

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.chipotlerep.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity(),HomeFragment.OnMenuItemClickListener {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = findViewById(R.id.viewPager2)

        // Initialize adapter for the ViewPager
        pagerAdapter = PagerAdapter(this)

        // Set the adapter for the ViewPager
        viewPager.adapter = pagerAdapter

        // Set offscreen page limit to ensure fragments are loaded
        viewPager.offscreenPageLimit = 2
        binding.menu.setOnClickListener {
            onMenuItemClick("MENU")

        }
        binding.featured.setOnClickListener {
            onMenuItemClick("FEATURED")

        }
        binding.order.setOnClickListener {
            onMenuItemClick("ORDER")

        }
        binding.rewards.setOnClickListener {
            onMenuItemClick("REWARDS")

        }
    }


    override fun onMenuItemClick(menuItem: String) {
        // Reset text colors for all TextViews
        binding.menu.setTextColor(ContextCompat.getColor(this, R.color.darkBrown))
        binding.featured.setTextColor(ContextCompat.getColor(this, R.color.darkBrown))
        binding.order.setTextColor(ContextCompat.getColor(this, R.color.darkBrown))
        binding.rewards.setTextColor(ContextCompat.getColor(this, R.color.darkBrown))

        // Change text color of selected TextView to red
        when (menuItem) {
            "MENU" -> binding.menu.setTextColor(ContextCompat.getColor(this, R.color.maroon))
            "FEATURED" -> binding.featured.setTextColor(ContextCompat.getColor(this, R.color.maroon))
            "ORDER" -> binding.order.setTextColor(ContextCompat.getColor(this, R.color.maroon))
            "REWARDS" -> binding.rewards.setTextColor(ContextCompat.getColor(this, R.color.maroon))
        }

        // Handle menu item click here
        // You can switch fragments based on the menuItem parameter
        // For example:
        when (menuItem) {
            "MENU" -> viewPager.setCurrentItem(0, true)
            "FEATURED" -> viewPager.setCurrentItem(1, true)
            "ORDER" -> viewPager.setCurrentItem(2, true)
            "REWARDS" -> viewPager.setCurrentItem(3, true)
        }
    }


    inner class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 4 // Number of fragments

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MenuFragment()
                1 -> FeaturedFragment()
                2 -> OrdersFragment()
                3 -> RewardsFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }


}

