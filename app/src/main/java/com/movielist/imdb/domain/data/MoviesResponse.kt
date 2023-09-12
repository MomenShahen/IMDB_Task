package com.movielist.imdb.domain.data


data class MoviesResponse(

    var page: Int = 1,
    var results: List<Movie>? = null,
    var total_pages: Int = 1,
    var total_results: Int = results?.size ?: 0

)