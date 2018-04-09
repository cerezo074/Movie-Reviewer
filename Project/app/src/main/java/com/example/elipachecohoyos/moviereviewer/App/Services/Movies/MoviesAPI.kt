package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import android.util.Log
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Configuration.ConfigurationResponseDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by elipachecohoyos on 6/04/18.
 */

internal class MoviesAPI: MoviesAPIInterface {

    private val networkApi = MoviesNetworkClient()
    private var moviesConfiguration: ConfigurationResponseDTO? = null

    private val LOG_MOVIE_API = "com.movie.api"

    init {
        fectchConfiguration()
    }

    override fun getFavorites(): Call<FavoriteResponseDTO> {
        return networkApi.getFavorites()
    }

    override fun createImageURI(path: String) : String {
        return ""
    }

    private fun fectchConfiguration() {
        val call = networkApi.getConfiguration()
        call.enqueue(object : Callback<ConfigurationResponseDTO> {

            override fun onResponse(call: Call<ConfigurationResponseDTO>?, response: Response<ConfigurationResponseDTO>?) {
                if (response != null && response.isSuccessful) {
                    moviesConfiguration = response.body()
                    Log.d("{$LOG_MOVIE_API}.response",moviesConfiguration.toString())
                    return
                }

                onError()
            }

            override fun onFailure(call: Call<ConfigurationResponseDTO>?, t: Throwable?) {
                onError(t.toString())
            }

            private fun onError(exception: String? = null) {
                Log.d("{$LOG_MOVIE_API}.error",  exception ?: "Error fetching configuration file")
            }

        })
    }

}