package com.example.codingchallengeoi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    private val api: Api

    init {
        val retrofit = Retrofit.Builder() //for constructing our required object. It needs the base
            // URL which is going to be used for every service call and a converter factory –
            // which takes care of the parsing of data we're sending and also the responses we get.
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            // converter factory is needed to convert tmdb data into java objects
            .build()

        api = retrofit.create(Api::class.java) //create an instance of a service
    }

    fun getPopularMovies(
        page: Int = 1, // only retrieve the first page
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> { // populating movies list based on order of queueing
            // callback lets us pass function into another function as an argument
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }   else {
                            onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
}