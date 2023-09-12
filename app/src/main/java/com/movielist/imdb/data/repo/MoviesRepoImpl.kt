package com.movielist.imdb.data.repo

import com.movielist.imdb.data.local.daos.MoviesDao
import com.movielist.imdb.data.remote.ApiInterface
import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.domain.repository.MoviesRepo
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val moviesDao: MoviesDao
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
//        Log.e("Result",Gson().toJson(response))
        return if (response.isSuccessful) {
            CoroutineScope(Dispatchers.IO).launch {
                response.body()?.results?.let { moviesDao.nukeTableAndAdd(it) }
            }
            Resource.Success(data = response.body() as MoviesResponse, message = "")
        } else {
            //                throw IOException(response.errorBody().toString())
            Resource.Error(errorData = response.errorBody().toString())
        }
//        }
    }

    override suspend fun getMovie(movieId: Int) = moviesDao.getMovie(movieId)

}