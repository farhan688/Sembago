package com.example.sembago.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sembago.R
import com.example.sembago.adapter.FavouriteAdapter
import com.example.sembago.data.Favourite

class FavouriteFragment : Fragment(R.layout.fragment_favourite){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        var favouriteList = mutableListOf(
            Favourite("bayem", 5000,4.2, 3),
            Favourite("wortel", 5500,4.6, 4),
            Favourite("kangkung", 7000,3.9, 2),
            Favourite("toge", 2000,4.4, 3),
            Favourite("seledri", 1000,4.8, 3),
            Favourite("timun", 5500,4.4, 9),
            Favourite("terong", 4000,4.1, 1)
        )

        val adapter = FavouriteAdapter(favouriteList)
    }
}