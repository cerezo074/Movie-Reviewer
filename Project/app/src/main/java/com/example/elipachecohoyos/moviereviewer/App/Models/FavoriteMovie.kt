package com.example.elipachecohoyos.moviereviewer.App.Models

import android.net.Uri
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteMovieDTO

/**
 * Created by elipachecohoyos on 9/04/18.
 */

sealed class PosterSize {
    data class Small(val size: String) : PosterSize()
    data class Medium(val size: String) : PosterSize()
    data class Large(val size: String) : PosterSize()
    data class ExtraLarge(val size: String) : PosterSize()
    data class Original(val size: String) : PosterSize()

    val value : String
        get () {
            return when (this) {
                is PosterSize.Small -> this.size
                is PosterSize.Medium -> this.size
                is PosterSize.Large -> this.size
                is PosterSize.ExtraLarge -> this.size
                is PosterSize.Original -> this.size
            }
        }

    companion object {

        fun createPosterSize(size: String) : PosterSize {
            return when (size) {
                "w92" -> PosterSize.Small(size)
                "w185" -> PosterSize.Medium(size)
                "w500" -> PosterSize.Large(size)
                "w800" -> PosterSize.ExtraLarge(size)
                else -> PosterSize.Original(size)
            }
        }
    }
}

class FavoriteMovie(private val movieDTO: FavoriteMovieDTO, private val rawPosterSizes: List<String>, private val baseURL: String) {

    var rating: String = movieDTO.popularity.toString()
    private  set

    val title: String = movieDTO.title
    val smallPoster: String
    val largePoster: String
    val extraLagerPoster: String

    init {
        val posterSizes = rawPosterSizes.map { PosterSize.createPosterSize(it) }
        val smallPosterSize = posterSizes.find { it is PosterSize.Small }
        smallPoster = if (smallPosterSize != null) { Uri.parse(baseURL + smallPosterSize.value + "/" + movieDTO.poster).toString() } else { " " }

        val largePosterSize = posterSizes.find { it is PosterSize.Large }
        largePoster = if (largePosterSize != null) { Uri.parse(baseURL + largePosterSize.value + "/" + movieDTO.poster).toString() } else { " " }

        val extraLagerPosterSize = posterSizes.find { it is PosterSize.ExtraLarge }
        extraLagerPoster = if (extraLagerPosterSize != null) { Uri.parse(baseURL + extraLagerPosterSize.value + "/" + movieDTO.poster).toString() } else { " " }
    }

    fun updateRating(title: String) {
        rating = title + ": " + rating
    }

}