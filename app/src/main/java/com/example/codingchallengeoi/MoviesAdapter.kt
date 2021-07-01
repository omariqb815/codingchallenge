package com.example.codingchallengeoi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class MoviesAdapter(

    private var movies: MutableList<Movie>, // mutable list lets us order and catalog the movies we retrieved
    private val onMovieClick: (movie: Movie) -> Unit // check for user clicking on the movie
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies = movies as MutableList<Movie>
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)

        fun bind(movie: Movie) {
            Glide.with(itemView)    // glide lets you load images
                .load("https://image.tmdb.org/t/p/w780${movie.posterPath}") // image url
                .transform(CenterCrop())
                .into(poster)   // places image in variable
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
        }
    }
}