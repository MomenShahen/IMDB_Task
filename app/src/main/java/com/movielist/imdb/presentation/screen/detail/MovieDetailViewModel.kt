package com.movielist.imdb.presentation.screen.detail

import androidx.lifecycle.ViewModel
import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.data.repo.MoviesRepoImpl
import com.movielist.imdb.utils.Constants.POSTER_BASE_URL
import com.movielist.imdb.utils.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesRepo: MoviesRepoImpl
) : ViewModel() {

    private val _movieId = MutableStateFlow(-1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movie = _movieId.mapLatest {
        moviesRepo.getMovie(it)
    }

    private val _openImdbUrl = mutableEventFlow<String>()
    val openImdbUrl: SharedFlow<String> = _openImdbUrl

    private val _shouldNavigateUp = mutableEventFlow<Boolean>()
    val shouldNavigateUp: SharedFlow<Boolean> = _shouldNavigateUp

    fun init(movieId: Int) {
        _movieId.tryEmit(movieId)
    }

    fun onOpenImdbClicked(movie: Movie) {
        _openImdbUrl.tryEmit("${POSTER_BASE_URL}${movie.posterPath}")
    }

    fun onBackPressed() {
        _shouldNavigateUp.tryEmit(true)
    }
}