package com.example.sembago.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sembago.R
import com.example.sembago.data.Favourite

class FavouriteAdapter(
    var favourites: List<Favourite>
) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    inner class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.itemView.apply {
        }
    }

    override fun getItemCount(): Int {
        return favourites.size
    }
}