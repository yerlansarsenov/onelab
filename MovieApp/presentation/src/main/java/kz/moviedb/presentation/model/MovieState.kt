package kz.moviedb.presentation.model

import kz.moviedb.domain.model.MovieResponse

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
sealed class MovieState {
    data class Response(val movie: MovieResponse): MovieState()
    data class Error(val message: String): MovieState()
    object ShowLoading: MovieState()
    object HideLoading: MovieState()
}