package com.movielist.imdb.data.repo

import com.movielist.imdb.data.local.daos.MoviesDao
import com.movielist.imdb.data.remote.ApiInterface
import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.domain.repository.MoviesRepo
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val moviesDao: MoviesDao
) : MoviesRepo {
    private var shouldLoadRemoteData = false

    override suspend fun getLocalMovies() = Resource.Success(
        data = MoviesResponse(
            results = moviesDao.getAllMovies().first()
        ),
        message = ""
    )

    override suspend fun getRemoteMovies(pageNumber: Int): Resource<MoviesResponse> {
        return if (!shouldLoadRemoteData) {
            shouldLoadRemoteData = true
            getLocalMovies()
        } else {
            val response = apiInterface.getMovies(pageNumber)
            if (response.isSuccessful) {
                saveMovies(response)
                Resource.Success(data = response.body() as MoviesResponse, message = "")
            } else {
                Resource.Error(errorData = response.errorBody().toString())
            }
        }
    }

    private fun saveMovies(response: Response<MoviesResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            response.body()?.results?.let { moviesDao.nukeTableAndAdd(it) }
        }
    }

    override suspend fun getMovie(movieId: Int) = moviesDao.getMovie(movieId)

}