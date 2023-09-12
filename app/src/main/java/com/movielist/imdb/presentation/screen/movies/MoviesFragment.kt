package com.movielist.imdb.presentation.screen.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.movielist.imdb.presentation.theme.TopCornTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Launch movie detail page
        viewModel.goToMovieDetail.asLiveData().observe(viewLifecycleOwner) { movieId ->
            if (movieId != -1) {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieId),
                )
            }
        }

        // Toggle darkMode
        viewModel.toggleDarkMode.asLiveData().observe(viewLifecycleOwner) {
            it?.let { isDark ->
                val flag = if (isDark) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(flag)
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                TopCornTheme {
                    MoviesScreen(viewModel)
                }
            }
        }
    }
}