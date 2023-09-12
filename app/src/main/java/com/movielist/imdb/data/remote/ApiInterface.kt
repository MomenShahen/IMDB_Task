package com.movielist.imdb.data.remote

import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top250_min.json")
    fun getTop250Movies(): Flow<Resource<List<Movie>>>

    @GET("/3/movie/popular?language=en-US")
    suspend fun getMovies(@Query("page") page: Int): Resource<MoviesResponse>
}