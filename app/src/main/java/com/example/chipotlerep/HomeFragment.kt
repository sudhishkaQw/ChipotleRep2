package com.example.chipotlerep

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.chipotlerep.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    interface OnMenuItemClickListener {
        fun onMenuItemClick(menuItem: String)
    }

    private var menuItemClickListener: OnMenuItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMenuItemClickListener) {
            menuItemClickListener = context
        } else {
            throw RuntimeException("$context must implement OnMenuItemClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the TextViews for the menu options
        val menuTextView = binding.menu
        val featuredTextView = binding.featured
        val orderTextView = binding.order
        val rewardsTextView = binding.rewards

        // Set click listeners for each menu option
        menuTextView.setOnClickListener {
            menuItemClickListener?.onMenuItemClick("MENU")

        }
        featuredTextView.setOnClickListener {
            menuItemClickListener?.onMenuItemClick("FEATURED")

        }
        orderTextView.setOnClickListener {
            menuItemClickListener?.onMenuItemClick("ORDER")

        }
        rewardsTextView.setOnClickListener {
            menuItemClickListener?.onMenuItemClick("REWARDS")

        }
    }


}
