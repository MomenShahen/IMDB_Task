package com.movielist.imdb.domain.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.movielist.imdb.data.local.daos.MoviesDao
import com.movielist.imdb.data.remote.ApiInterface
import com.movielist.imdb.data.repo.MoviesRepoImpl
import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.domain.repository.MoviesRepo
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRepositoryTest {

    // Add this rule to test LiveData or Flow with coroutines
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock the MoviesDao and any other dependencies
    @Mock
    private lateinit var moviesDao: MoviesDao

    @Mock
    private lateinit var apiInterface: ApiInterface

    private lateinit var movieRepository: MoviesRepo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MoviesRepoImpl(apiInterface,moviesDao)
    }

    @Test
    fun getLocalMoviesShouldReturnSuccess() = runBlocking {
        // Arrange
        val mockMoviesResponse = MoviesResponse(results = listOf(/* your test movie data */))
        Mockito.`when`(moviesDao.getAllMovies()).thenReturn(flowOf(mockMoviesResponse.results))

        // Act
        val result = movieRepository.getLocalMovies()

        // Assert
        assertEquals(Resource.Success(data = mockMoviesResponse, message = ""), result)
    }
}
