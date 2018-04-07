package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteResponseDTO
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by elipachecohoyos on 6/04/18.
 */

internal class MoviesAPI: MoviesAPIInterface {

    private val networkApi = MoviesNetworkClient()

    override fun getFavorites(): Call<FavoriteResponseDTO> {
        return networkApi.getFavorites()
    }

}