package com.example.sembago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class Walkthrough2Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_walkthrough2, container, false)

        val btnSkip = view.findViewById<View>(R.id.btnSkip)
        btnSkip.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager2)
            viewPager?.setCurrentItem(2, true)
        }

        return view
    }
}


