package com.movielist.imdb.domain.usecase

import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.domain.repository.MoviesRepo
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetMoviesUseCaseTest {
    private lateinit var usecase: GetMoviesUseCase
    private lateinit var repo: MoviesRepo // Replace with your actual repository interface

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repo = mock(MoviesRepo::class.java)
        usecase = GetMoviesUseCase(repo)
    }

    @Test
    fun invokeShouldCallGetRemoteMoviesFromRepository() = runBlocking {
        // Arrange
        val pageNumber = 1
        val expectedResponse = Resource.Success(message = "", data = MoviesResponse())
        `when`(repo.getRemoteMovies(pageNumber)).thenReturn(expectedResponse)

        // Act
        val result = usecase(pageNumber)

        // Assert
        verify(repo).getRemoteMovies(pageNumber)
        assertEquals(expectedResponse, result)
    }
}
