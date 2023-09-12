package com.movielist.imdb.presentation.screen.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.movielist.imdb.presentation.theme.TopCornTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.init(args.movieId)

        viewModel.openImdbUrl.asLiveData().observe(viewLifecycleOwner) { imdbUrl ->
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(imdbUrl)
                )
            )
        }

        val navController = findNavController()
        viewModel.shouldNavigateUp.asLiveData().observe(viewLifecycleOwner) {
            if (it) {
                navController.navigateUp()
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                TopCornTheme {
                    MovieDetailScreen(
                        viewModel
                    )
                }
            }
        }
    }
}