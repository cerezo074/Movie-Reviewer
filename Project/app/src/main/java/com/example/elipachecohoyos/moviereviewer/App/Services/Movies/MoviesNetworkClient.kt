package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import android.net.Uri
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteResponseDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by elipachecohoyos on 6/04/18.
 */
class MoviesNetworkClient {

    private val apiKey: String = "3d3707fe2cfcfb854662694a45fa7ef8"

    fun getFavorites(): Call<FavoriteResponseDTO> {
        return moviesServices.discoverMovies(apiKey)
    }

    companion object {
        private val retrofitClient: Retrofit
        private val moviesServices: MoviesServiceDefinitions

        init {
            val uri = Uri.Builder()
            uri.scheme("https")
            uri.authority("api.themoviedb.org")

            val moviesBaseUrl = uri.build().toString()

            retrofitClient = Retrofit.Builder()
                    .baseUrl(moviesBaseUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            moviesServices = retrofitClient.create(MoviesServiceDefinitions::class.java)
        }

    }

}