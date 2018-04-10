package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import android.net.Uri
import android.util.Log
import com.example.elipachecohoyos.moviereviewer.App.Models.PosterSize
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Configuration.ConfigurationResponseDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by elipachecohoyos on 6/04/18.
 */

internal class MoviesAPI: MoviesAPIInterface {

    override val baseURL: String
        get() = moviesConfiguration?.images?.baseURL ?: ""

    override val rawPosterSizes: List<String>
        get() = moviesConfiguration?.images?.rawPosterSizes ?: listOf()

    private val networkApi = MoviesNetworkClient()
    private var moviesConfiguration: ConfigurationResponseDTO? = null
    private val LOG_MOVIE_API = "com.movie.api"

    init {
        fetchConfiguration()
    }

    override fun getFavorites(): Call<FavoriteResponseDTO> {
        return networkApi.getFavorites()
    }

    //TODO: Should update or notify to the classes that retain this class to reload their UI(e.g. images are ready to download)
    private fun fetchConfiguration() {
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