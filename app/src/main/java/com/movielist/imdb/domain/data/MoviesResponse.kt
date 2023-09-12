package com.movielist.imdb.domain.data


data class MoviesResponse(

    var page: Int = 1,
    var results: List<Movie>? = null,
    var totalPages: Int = 1,
    var totalResults: Int = results?.size ?: 0

)