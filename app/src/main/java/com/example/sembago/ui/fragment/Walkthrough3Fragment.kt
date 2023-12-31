package com.example.sembago.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sembago.R
import com.example.sembago.ui.activity.SigninActivity

class Walkthrough3Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_walkthrough3, container, false)

        val btnAyobelanja = view.findViewById<View>(R.id.btnAyobelanja)
        btnAyobelanja.setOnClickListener {
            val intent = Intent(activity, SigninActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}


