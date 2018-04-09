package com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Configuration

import com.squareup.moshi.Json

/**
 * Created by elipachecohoyos on 9/04/18.
 */
data class ConfigurationResponseDTO(@Json(name = "images") val images: ImageConfigurationDTO)