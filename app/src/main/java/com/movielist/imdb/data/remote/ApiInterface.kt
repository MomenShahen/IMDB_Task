package com.movielist.imdb.data.remote

import com.movielist.imdb.domain.data.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular?language=en-US")
    suspend fun getMovies(@Query("page") page: Int): Response<MoviesResponse>
}