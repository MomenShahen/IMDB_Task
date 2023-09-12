package com.movielist.imdb.utils.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}