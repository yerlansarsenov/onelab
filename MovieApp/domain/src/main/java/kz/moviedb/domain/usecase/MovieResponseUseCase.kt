package kz.moviedb.domain.usecase

import kz.moviedb.domain.repository.MovieRepository

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class MovieResponseUseCase(private val repository: MovieRepository) {
    suspend fun getMovieById(id: String) = repository.getMovieById(id)
}