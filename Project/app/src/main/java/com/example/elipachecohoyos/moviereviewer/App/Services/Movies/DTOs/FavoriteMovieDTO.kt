package com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs

import com.squareup.moshi.Json

/**
 * Created by elipachecohoyos on 6/04/18.
 */

data class FavoriteMovieDTO(@Json(name = "id") val id: Int,
                            @Json(name = "title") val title: String,
                            @Json(name = "popularity") val popularity: Double,
                            @Json(name = "poster_path") val poster: String)