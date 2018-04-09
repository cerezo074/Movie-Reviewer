package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Configuration.ConfigurationResponseDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by elipachecohoyos on 6/04/18.
 */

internal interface MoviesServiceDefinitions {

    @GET("/3/discover/movie")
    fun discoverMovies(@Query("api_key") apiKey: String) : Call<FavoriteResponseDTO>

    @GET(value = "3/configuration")
    fun fetchConfiguration(@Query(value = "api_key") apiKey: String) : Call<ConfigurationResponseDTO>

}