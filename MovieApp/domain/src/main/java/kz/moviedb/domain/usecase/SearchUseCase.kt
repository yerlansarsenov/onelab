package kz.moviedb.domain.usecase

import kz.moviedb.domain.repository.MovieRepository

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class SearchUseCase(private val repository: MovieRepository) {
    suspend fun getMovieBySearch(text: String) = repository.getMovieBySearch(text)
}