package com.example.elipachecohoyos.moviereviewer.App.View.MovieDetail

import com.example.elipachecohoyos.moviereviewer.App.Models.Movie
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.elipachecohoyos.moviereviewer.App.View.Home.HomeActivity
import com.example.elipachecohoyos.moviereviewer.R

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie_detail)

        inflateViews()
        loadDataForMovie()
    }

    fun inflateViews() {

    }

    fun loadDataForMovie() {
        val bundle = intent.extras
        val movie = bundle?.getParcelable<Movie>(HomeActivity.FAVORITE_MOVIE_KEY) ?: return

    }

}
