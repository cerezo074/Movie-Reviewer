package com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Configuration

import com.squareup.moshi.Json

/**
 * Created by elipachecohoyos on 9/04/18.
 */
data class ImageConfigurationDTO (@Json(name = "base_url") val baseURL: String,
                                  @Json(name = "poster_sizes") val posterSize: List<String>)