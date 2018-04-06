package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import android.util.Log
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by elipachecohoyos on 6/04/18.
 */

internal class MoviesAPI: MoviesAPIInterface {

    private val networkApi = MoviesNetworkClient()

    override fun getFavorites() {
        val call = networkApi.getFavorites()
        call.enqueue(object : Callback<FavoriteResponseDTO> {

            override fun onResponse(call: Call<FavoriteResponseDTO>?, response: Response<FavoriteResponseDTO>?) {
                if (response != null && response.isSuccessful) {

                    val favoriteMovies = response.body()?.results ?: doOnError(); return
                    Log.d("com.api.movie.response",favoriteMovies.toString())
                }

                doOnError()
            }

            override fun onFailure(call: Call<FavoriteResponseDTO>?, t: Throwable?) {
                doOnError()
            }

            private fun doOnError() {
                Log.d("com.api.movie.error", "Error")
            }

        })
    }

}