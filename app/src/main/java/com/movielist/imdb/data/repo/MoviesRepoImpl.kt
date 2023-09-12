package com.movielist.imdb.data.repo

import android.util.Log
import com.google.gson.Gson
//import com.movielist.imdb.data.local.daos.MoviesDao
import com.movielist.imdb.data.remote.ApiInterface
import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.domain.repository.MoviesRepo
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val apiInterface: ApiInterface,
//    private val moviesDao: MoviesDao
) : MoviesRepo {
    private var shouldLoadRemoteData = false

    override suspend fun getLocalMovies() = Resource.Success(
        data = MoviesResponse(
            results = emptyList()
        ),
        message = ""
    )

    override suspend fun getRemoteMovies(pageNumber: Int): Resource<MoviesResponse> {
//        return if (!shouldLoadRemoteData) {
//            shouldLoadRemoteData = true
//            getLocalMovies()
//        } else {
            val response = apiInterface.getMovies(pageNumber)
            if (response is Resource.Success) {
//                response.data.results?.let { moviesDao.nukeTableAndAdd(it) }
            } else if (response is Resource.Error) {
                throw IOException(response.errorData)
            }
        Log.e("Result",Gson().toJson(response))

            return response
//        }
    }

    override suspend fun getMovie(movieId: Int) = Movie()

}