package com.example.elipachecohoyos.moviereviewer.App.Services.Movies

import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.FavoriteResponseDTO
import retrofit2.Call

/**
 * Created by elipachecohoyos on 6/04/18.
 */

public interface MoviesAPIInterface {

    //TODO: Return and observable with rx
    fun getFavorites() : Call<FavoriteResponseDTO>

}