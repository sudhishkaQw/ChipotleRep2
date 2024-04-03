package com.example.chipotlerep

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chipotlerep.databinding.FragmentHomeBinding
import com.example.chipotlerep.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        // Find the ImageView by its ID
        val backButton = binding.cross

        // Set OnClickListener to the ImageView
        backButton.setOnClickListener {
            val secondFragment = HomeFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, secondFragment)
            transaction.addToBackStack(null) // Add transaction to back stack
            transaction.commit()
        }
        return view
    }


}

