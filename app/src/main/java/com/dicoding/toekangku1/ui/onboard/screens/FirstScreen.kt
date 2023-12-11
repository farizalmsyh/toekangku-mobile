package com.dicoding.toekangku1.ui.onboard.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.toekangku1.R
class FirstScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first_screen, container, false)

        val next = view.findViewById<Button>(R.id.btn_next_first)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        next.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }
}