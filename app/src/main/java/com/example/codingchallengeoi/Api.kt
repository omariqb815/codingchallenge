package com.example.codingchallengeoi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    //GET is used to request data from TMDB's popular movies section
    @GET("movie/popular")
    fun getPopularMovies( // use Query to access data with api key
        @Query("api_key") apiKey: String = "3f818d6ec363e350211868e8b0fd156b",
        @Query("page") page: Int  // this is for how many pages will be retrieved
    ): Call<GetMoviesResponse>
}