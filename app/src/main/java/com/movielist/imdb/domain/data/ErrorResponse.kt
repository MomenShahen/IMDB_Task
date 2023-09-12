package com.movielist.imdb.domain.data

data class ErrorResponse(
    val errorMessage: String = ""
) : Exception()