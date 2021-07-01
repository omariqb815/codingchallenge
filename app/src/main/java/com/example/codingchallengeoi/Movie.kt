package com.example.codingchallengeoi

import com.google.gson.annotations.SerializedName

data class Movie(
    // variables for the movie details activity
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String
)