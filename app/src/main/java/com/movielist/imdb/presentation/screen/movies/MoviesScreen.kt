package com.movielist.imdb.presentation.screen.movies

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movielist.imdb.R
import com.movielist.imdb.domain.data.Movie
import com.movielist.imdb.domain.data.ScreenState
import com.movielist.imdb.presentation.common.Fakes
import com.movielist.imdb.presentation.common.Poster
import com.movielist.imdb.presentation.common.RetryMessage
import com.movielist.imdb.presentation.theme.TopCornTheme
import com.movielist.imdb.utils.isFailureState
import com.movielist.imdb.utils.loadingState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel
) {
    val screenState = moviesViewModel.moviesState

    val currentUiMode = LocalConfiguration.current.uiMode

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp,
                modifier = Modifier.requiredHeight(70.dp),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 28.sp
                    )
                },
                actions = {
                    AppBarMenu(
                        onToggleDarkModelClicked = {
                            val isDark = currentUiMode and Configuration.UI_MODE_NIGHT_MASK ==
                                    Configuration.UI_MODE_NIGHT_YES
                            moviesViewModel.onToggleDarkModeClicked(isDark)
                        }
                    )
                }
            )
        }
    ) {
        Surface {
            BodyContent(
                screenState = screenState,
                onMovieClicked = {
                    moviesViewModel.onMovieClicked(it)
                }
            ) {
                moviesViewModel.onRetryClicked()
            }
        }
    }
}

@Composable
fun AppBarMenu(
    onToggleDarkModelClicked: () -> Unit
) {
    // Dark Mode
    IconButton(
        onClick = {
            onToggleDarkModelClicked()
        }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_switch_dark_mode),
            contentDescription = null
        )
    }
}

@Composable
fun BodyContent(
    screenState: ScreenState<Movie>,
    onMovieClicked: (Movie) -> Unit,
    onRetryClicked: () -> Unit
) {

    when {
        loadingState(screenState) -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        isFailureState(screenState) -> {
            RetryMessage(
                message = screenState.error,
                onRetryClicked = onRetryClicked
            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(screenState.items) { _, movie ->
                    MovieItem(movie = movie, onMovieClicked = onMovieClicked)
                }
            }
        }
    }
}


private val cardWidth = 150.dp

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .requiredWidth(cardWidth)
            .padding(10.dp)
    ) {

        // Poster
        Poster(
            modifier = Modifier
                .requiredWidth(cardWidth)
                .requiredHeight(200.dp),
            movie = movie,
            onMovieClicked = onMovieClicked
        )

        // Title
        movie.title?.let {
            Text(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Rating
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Star
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .requiredSize(12.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
                tint = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                contentDescription = null
            )

            // Rating
            Text(
                text = movie.voteAverage.toString(),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(top = 2.dp)
            )

        }
    }
}


@Preview
@Composable
fun PreviewMovie() {
    TopCornTheme {
        MovieItem(
            movie = Fakes.getFakeMovie(),
            onMovieClicked = { /*TODO*/ }
        )
    }
}

