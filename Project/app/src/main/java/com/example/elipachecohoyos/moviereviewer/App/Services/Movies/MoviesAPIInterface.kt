package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import com.example.elipachecohoyos.moviereviewer.App.Models.PosterSize
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Configuration.ConfigurationResponseDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteResponseDTO
import retrofit2.Call

/**
 * Created by elipachecohoyos on 6/04/18.
 */

public interface MoviesAPIInterface {

    val rawPosterSizes: List<String>
    val baseURL: String

    //TODO: Return and observable with rx
    fun getFavorites() : Call<FavoriteResponseDTO>
}