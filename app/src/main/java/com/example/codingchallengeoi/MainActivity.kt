package com.example.codingchallengeoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // "AppCompatActivity is a class from v7 Appcompat library.
    // This is a compatibility library that back ports some features of recent versions of
    // Android to older devices."
    private lateinit var popularMovies: RecyclerView
    // recyclerview lets you *only* load pictures that are visible on screen
    // more efficient for long lists
    private lateinit var popularMoviesAdapter: MoviesAdapter

    private fun showMovieDetails(movie: Movie) {
        // intent lets us pass data from one activity (screen) to another
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }
    // set the layout of the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularMovies = findViewById(R.id.popular_movies)
        popularMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,   // vertical single column layout
            false
        )
        popularMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter

        MoviesRepository.getPopularMovies( //check for successful retrieval of data
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.updateMovies(movies)
    }

    private fun onError() {
        // if there is an issue, display the error message found in strings.xml. toast.length_short
        // is used for a short notification
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}