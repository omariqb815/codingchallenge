package com.example.codingchallengeoi

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    // SerializedName takes original names lets us name it to what we want
    @SerializedName("page") val page: Int, // how many pages of movies we want (in this case: one)
    @SerializedName("results") val movies: List<Movie> // movies we retrieved from tmdb
)