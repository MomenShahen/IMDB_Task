package com.movielist.imdb.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.movielist.imdb.domain.data.Movie

@Composable
fun Poster(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClicked: (Movie) -> Unit
) {
    Card(
        modifier = Modifier.clickable(onClick = { onMovieClicked(movie) })
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = movie.posterPath)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        scale(Scale.FILL)
                    }).build()
            ),
            modifier = modifier,
            contentDescription = null
        )
    }
}