package kz.moviedb.lab1.model

/**
 * Created by Sarsenov Yerlan on 14.01.2021.
 */
sealed class SearchResponseState {
    data class SearchResult(val search: SearchResponse): SearchResponseState()
    data class Error(val error: String): SearchResponseState()
}

sealed class MovieResponseState {
    data class SearchResult(val movie: MovieResponse): MovieResponseState()
    data class Error(val error: String): MovieResponseState()
}