package com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs

import com.squareup.moshi.Json

/**
 * Created by elipachecohoyos on 6/04/18.
 */
data class FavoriteResponseDTO(@Json (name = "results") val results: List<FavoriteMovieDTO>)