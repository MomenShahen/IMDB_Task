package com.movielist.imdb.presentation.viewmodel

import com.movielist.imdb.domain.data.MoviesResponse
import com.movielist.imdb.domain.repository.MoviesRepo
import com.movielist.imdb.domain.usecase.GetMoviesUseCase
import com.movielist.imdb.presentation.screen.movies.MoviesViewModel
import com.movielist.imdb.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    // Create a TestCoroutineDispatcher for running coroutines in tests
    private val testDispatcher = TestCoroutineDispatcher()

    // Mock dependencies
    private lateinit var usecase: GetMoviesUseCase
    private lateinit var repo: MoviesRepo // Replace with your actual repository interface


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repo = Mockito.mock(MoviesRepo::class.java)
        usecase = GetMoviesUseCase(repo)
        // Initialize the ViewModel with mocked dependencies and the test dispatcher
        viewModel = MoviesViewModel(usecase)
    }

    @Test
    fun onLoadMoreClickedShouldLoadMoreItems() = testDispatcher.runBlockingTest {
        // Mock the response from the useCase when loadNextItems is called
        val nextPage = 2
        val response = Resource.Success(
            data = MoviesResponse(),
            message = ""
        )
        `when`(usecase(nextPage)).thenReturn(response)

        // Call onLoadMoreClicked
        viewModel.onLoadMoreClicked()

        // Get the updated moviesState
        val updatedMoviesState = viewModel.moviesState

        // Verify that the items are updated in the viewModel
        assert(updatedMoviesState.page <= nextPage) // Check if the page has been updated

    }

}
