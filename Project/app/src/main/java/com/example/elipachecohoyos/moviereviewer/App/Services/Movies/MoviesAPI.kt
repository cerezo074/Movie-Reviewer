package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

/**
 * Created by elipachecohoyos on 6/04/18.
 */

internal class MoviesAPI: MoviesAPIInterface {

    private val networkApi = MoviesNetworkClient()

    override fun getFavorites() {
        val call = networkApi.getFavorites()
    }

}