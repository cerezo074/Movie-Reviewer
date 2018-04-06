package com.example.elipachecohoyos.moviereviewer.App.Services

import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.MoviesAPI
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.MoviesAPIInterface

/**
 * Created by elipachecohoyos on 6/04/18.
 */



class APIManager {


    companion object {

        val moviesManager: MoviesAPIInterface = MoviesAPI()

    }

}