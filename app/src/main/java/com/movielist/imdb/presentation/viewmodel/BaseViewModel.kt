package com.movielist.imdb.presentation.viewmodel


import androidx.lifecycle.ViewModel
import coil.network.HttpException
import com.movielist.imdb.domain.data.ErrorResponse
import com.movielist.imdb.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.EOFException
import java.net.UnknownHostException


abstract class BaseViewModel : ViewModel() {

    inline fun CoroutineScope.launchCatching(
        noinline block: suspend CoroutineScope.() -> Unit,
        crossinline onError: (ErrorResponse) -> Unit,
    ) {
        launch(
            CoroutineExceptionHandler { _, throwable ->
                val errorResponse: ErrorResponse = when (throwable) {
                    is EOFException -> ErrorResponse()
                    is ErrorResponse -> throwable
                    is HttpException -> ErrorResponse(
                        errorMessage = Constants.NETWORK_ERROR,
                    )

                    is UnknownHostException -> ErrorResponse(
                        errorMessage = Constants.NO_INTERNET,
                    )

                    else -> ErrorResponse(
                        errorMessage = Constants.SOMETHING_WENT_WRONG,
                    )
                }
                if (errorResponse != ErrorResponse())
                    onError(errorResponse)
            },
            block = block
        )
    }
}
