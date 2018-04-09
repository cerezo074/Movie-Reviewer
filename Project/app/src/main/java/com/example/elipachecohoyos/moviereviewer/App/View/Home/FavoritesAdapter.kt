package com.example.elipachecohoyos.moviereviewer.App.View.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.elipachecohoyos.moviereviewer.R
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteMovieDTO


/**
 * Created by elipachecohoyos on 6/04/18.
 */
class FavoritesAdapter(favoritesData: List<FavoriteMovieDTO>? = null, itemTapDelegate: FavoriteListDelegate? = null): RecyclerView.Adapter<FavoritesAdapter.FavoriteMovieViewHolder>() {

    private var favorites: List<FavoriteMovieDTO>? = favoritesData
    private var tapDeleage: FavoriteListDelegate? = itemTapDelegate

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FavoriteMovieViewHolder {
        val viewHolder = LayoutInflater.from(parent?.context).inflate(R.layout.home_favorite_item, parent, false)
        return FavoriteMovieViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return favorites?.count() ?: 0
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder?, position: Int) {
        val favorites = favorites ?: return
        val favoriteMovie = favorites[position]
        holder?.bind(favoriteMovie)
        holder?.delegate = tapDeleage
    }

    fun update(data: List<FavoriteMovieDTO>?) {
        favorites = data
        notifyDataSetChanged()
    }

    inner class FavoriteMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val titleTextView: TextView
        private val ratingTextView: TextView
        private val poster: ImageView
        var delegate: FavoriteListDelegate? = null

        init {
            titleTextView = itemView.findViewById(R.id.favorite_movie_title) as TextView
            ratingTextView = itemView.findViewById(R.id.favorite_movie_rating) as TextView
            poster = itemView.findViewById(R.id.favorite_movie_image) as ImageView
            itemView.setOnClickListener(this)
        }

        fun bind(favorite: FavoriteMovieDTO) {
            titleTextView.text = favorite.title
            ratingTextView.text = favorite.popularity.toString()
        }

        override fun onClick(v: View?) {
            delegate?.didTouchItem(adapterPosition)
        }

    }

}

interface FavoriteListDelegate {
    fun didTouchItem(index: Int)
}

