package com.movielist.imdb.utils.pagination

class DefaultPaginator<Key, T>(
    private val initialPage: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val getNextPage: suspend () -> Key,
    private inline val onRequest: suspend (nextPage: Key) -> List<T>,
    private inline val onSuccess: suspend (items: List<T>, newPage: Key) -> Unit
) : Paginator<Key, T> {

    private var currentPage: Key = initialPage
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentPage)
        isMakingRequest = false
        currentPage = getNextPage()
        onSuccess(result, currentPage)
        onLoadUpdated(false)
    }

    override suspend fun reset() {
        currentPage = initialPage
        isMakingRequest = false
    }
}