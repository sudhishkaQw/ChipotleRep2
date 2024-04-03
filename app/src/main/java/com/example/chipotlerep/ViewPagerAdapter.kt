package com.example.chipotlerep

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
          //  0 -> MenuFragment.newInstance()
            1 -> FeaturedFragment.newInstance("Content for Tab 2")
            2 -> OrdersFragment.newInstance("Content for Tab 3")
            3->RewardsFragment.newInstance()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
    companion object {
        private const val NUM_TABS = 3
    }
}