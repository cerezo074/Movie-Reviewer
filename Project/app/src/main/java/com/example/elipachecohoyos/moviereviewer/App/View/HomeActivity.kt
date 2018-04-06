package com.example.elipachecohoyos.moviereviewer.App.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.elipachecohoyos.moviereviewer.App.Services.APIManager
import com.example.elipachecohoyos.moviereviewer.App.Services.Movies.MoviesAPIInterface
import com.example.elipachecohoyos.moviereviewer.R

class HomeActivity : AppCompatActivity() {

    private lateinit var moviesManager: MoviesAPIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        moviesManager = APIManager.moviesManager
        moviesManager.getFavorites()
    }
}
