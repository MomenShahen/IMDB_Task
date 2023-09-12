package com.movielist.imdb.domain.data

data class ScreenState<T>(
    val isInitialized: Boolean = false,
    val error: String = "",
    val isLoading: Boolean = false,
    val items: MutableList<T> = mutableListOf(),
    val endReached: Boolean = false,
    val page: Int = 1,
)