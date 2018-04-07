package com.example.elipachecohoyos.moviereviewer.App.View.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elipachecohoyos.moviereviewer.R
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteMovieDTO


/**
 * Created by elipachecohoyos on 6/04/18.
 */
class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoriteMovieViewHolder>() {

    private var favorites: List<FavoriteMovieDTO>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FavoriteMovieViewHolder {
        val viewHolder = LayoutInflater.from(parent?.context).inflate(R.layout.home_favorite_item, parent, false)
        return FavoriteMovieViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return favorites?.count() ?: 0
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder?, position: Int) {
        val favorites = favorites ?: return
        val favoriteMovie = favorites.get(position)
    }

    fun update(data: List<FavoriteMovieDTO>) {
        favorites = data
        notifyDataSetChanged()
    }

    inner class FavoriteMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(favorite: FavoriteMovieDTO) {

        }

    }

}

