package com.example.elipachecohoyos.moviereviewer.App.View.Home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.example.elipachecohoyos.moviereviewer.App.Services.APIManager
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteResponseDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.MoviesAPIInterface
import com.example.elipachecohoyos.moviereviewer.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var loader: ProgressBar
    private lateinit var listStatusMessage: TextView
    private lateinit var favoritesList: RecyclerView
    private val favoriteListAdapter = FavoritesAdapter()

    private val viewModel: MovieViewModel = MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        inflateViews()
        configList()
        starFavorites()
    }

    private fun inflateViews() {
        favoritesList = findViewById(R.id.movie_list) as RecyclerView
        loader = findViewById(R.id.movie_list_loader) as ProgressBar
        listStatusMessage = findViewById(R.id.movie_list_status) as TextView
    }

    private fun configList() {
        favoritesList.layoutManager = LinearLayoutManager(this)
        favoritesList.hasFixedSize()
        favoritesList.adapter = favoriteListAdapter
    }

    private fun starFavorites() {

    }

    //TODO: Move this class to its folder and expose an observable
    private inner class MovieViewModel(movieAPIManager: MoviesAPIInterface? = null) {

        private val LOG_MOVIE_API = "com.api.movie"
        private val moviesManager: MoviesAPIInterface

        init {
            moviesManager = movieAPIManager ?: APIManager.moviesManager
        }

        fun getFavoritesData() {
            val call = moviesManager.getFavorites()
            call.enqueue(object : Callback<FavoriteResponseDTO> {

                override fun onResponse(call: Call<FavoriteResponseDTO>?, response: Response<FavoriteResponseDTO>?) {
                    if (response != null && response.isSuccessful) {

                        val favoriteMovies = response.body()?.results ?: doOnError(); return
                        Log.d("{$LOG_MOVIE_API}.response",favoriteMovies.toString())
                    }

                    doOnError()
                }

                override fun onFailure(call: Call<FavoriteResponseDTO>?, t: Throwable?) {
                    doOnError()
                }

                private fun doOnError() {
                    Log.d("{$LOG_MOVIE_API}.error", "Error")
                }

            })
        }

    }
}
