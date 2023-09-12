package com.movielist.imdb.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.domain.data.ScreenState

fun isNotInitializedState(screenState: ScreenState<Movie>) =
    !screenState.isInitialized

fun loadingState(screenState: ScreenState<Movie>) =
    screenState.isLoading && screenState.items.isEmpty()

fun isFailureState(screenState: ScreenState<Movie>) =
    !screenState.isLoading && screenState.error.isNotEmpty()

fun isEmptyState(screenState: ScreenState<Movie>) =
    !screenState.isLoading && screenState.items.isEmpty()

fun isLoadingMoreState(screenState: ScreenState<Movie>) =
    screenState.isLoading && screenState.items.isNotEmpty()

fun isSuccessDataState(screenState: ScreenState<Movie>) =
    !screenState.isLoading && screenState.items.isNotEmpty()

@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridState.isScrolledToEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
