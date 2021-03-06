package com.example.elipachecohoyos.moviereviewer.App.View.Home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.elipachecohoyos.moviereviewer.App.Models.FavoriteMovie
import com.example.elipachecohoyos.moviereviewer.App.Models.Movie
import com.example.elipachecohoyos.moviereviewer.App.Services.APIManager
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteMovieDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.DTOs.Discover.FavoriteResponseDTO
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.MoviesAPIInterface
import com.example.elipachecohoyos.moviereviewer.App.View.MovieDetail.MovieDetailActivity
import com.example.elipachecohoyos.moviereviewer.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), FavoriteListDelegate {

    private lateinit var loader: ProgressBar
    private lateinit var listStatusMessage: TextView
    private lateinit var favoritesList: RecyclerView
    private var favoriteListAdapter: FavoritesAdapter? = FavoritesAdapter(itemTapDelegate = this)

    private val viewModel: MovieViewModel = MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        inflateViews()
        configList()
        loadData()
    }

    private fun inflateViews() {
        favoritesList = findViewById(R.id.movie_list) as RecyclerView
        loader = findViewById(R.id.movie_list_loader) as ProgressBar
        listStatusMessage = findViewById(R.id.movie_list_status) as TextView
    }

    private fun configList() {
        favoritesList.layoutManager = LinearLayoutManager(this)
        favoritesList.hasFixedSize()
        favoritesList.adapter = favoriteListAdapter
    }

    private fun loadData() {
        listStatusMessage.text = resources.getString(R.string.loading_favorites)
        viewModel.getFavoritesData()
    }

    private fun shouldShowList(show: Boolean) {
        favoritesList.visibility = if (show) {  View.VISIBLE } else { View.INVISIBLE }
        listStatusMessage.visibility = if (show) { View.INVISIBLE } else { View.VISIBLE}
    }

    private fun refreshUI() {
        loader.visibility = View.INVISIBLE

        val data = viewModel.favoriteMovies
        val dataIsOk = data != null && !data.isEmpty()
        shouldShowList(dataIsOk)

        favoriteListAdapter?.update(data)
    }

    override fun didTouchItem(index: Int) {
        val movie = viewModel.getBasicMovieData(index) ?: return
        val movieDetailIntent = Intent(this, MovieDetailActivity::class.java)
        movieDetailIntent.putExtra(FAVORITE_MOVIE_KEY, movie)

        startActivity(movieDetailIntent)
    }

    companion object {
        @JvmStatic
        val FAVORITE_MOVIE_KEY = "com.activity.home.favorite_movie"
    }

    //TODO: Move this class to its folder and use streams or observables to make the asynchoronous comunications
    private inner class MovieViewModel(movieAPIManager: MoviesAPIInterface? = null) {

        private val LOG_MOVIE_API = "com.api.movie"
        private val moviesManager: MoviesAPIInterface
        private var favoriteMoviesDTO: List<FavoriteMovieDTO>? = null

        var favoriteMovies: List<FavoriteMovie>? = null
            get() {
                val list = favoriteMoviesDTO?.map { FavoriteMovie(it, moviesManager.rawPosterSizes, moviesManager.baseURL) }
                val ratingTitle = resources.getString(R.string.rating)
                list?.forEach { it.updateRating(ratingTitle) }

                return list
            }
            private set


        init {
            moviesManager = movieAPIManager ?: APIManager.moviesManager
        }

        fun getFavoritesData() {
            val call = moviesManager.getFavorites()
            call.enqueue(object : Callback<FavoriteResponseDTO> {

                override fun onResponse(call: Call<FavoriteResponseDTO>?, response: Response<FavoriteResponseDTO>?) {
                    if (response != null && response.isSuccessful) {
                        processResponse(response.body()?.results)
                    }

                    processError()
                }

                override fun onFailure(call: Call<FavoriteResponseDTO>?, t: Throwable?) {
                    processError(t.toString())
                }

            })
        }

        fun getBasicMovieData(index: Int) : Movie? {
            val favoriteMovie = favoriteMovies?.get(index) ?: return null

            return Movie(favoriteMovie.identifier, favoriteMovie.title, favoriteMovie.rating, favoriteMovie.largePoster)
        }

        private fun processResponse(response: Any?) {
            if (response == null) {
                processError()
                return
            }

            val favoriteMovies = response as? List<FavoriteMovieDTO>
            val sortedFavoritesMovies = favoriteMovies?.sortedByDescending { it.popularity }
            favoriteMoviesDTO = sortedFavoritesMovies

            didRefreshData()
            Log.d("{$LOG_MOVIE_API}.response",favoriteMovies.toString())
        }

        private fun didRefreshData() {
            refreshUI()
        }

        private fun processError(exception: String? = null) {
            listStatusMessage.text = resources.getString(R.string.error_loading_favorites)
            Log.d("{$LOG_MOVIE_API}.error", exception ?: "Error fetching the favorites data")
        }

    }
}
