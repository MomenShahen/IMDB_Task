package com.movielist.imdb.domain.repository

import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.utils.Resource

interface MoviesRepo {
    suspend fun getLocalMovies(): Resource<MoviesResponse>
    suspend fun getRemoteMovies(pageNumber: Int): Resource<MoviesResponse>
    suspend fun getMovie(movieId: Int): Movie?
}