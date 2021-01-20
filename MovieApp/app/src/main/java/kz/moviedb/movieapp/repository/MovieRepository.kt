package kz.moviedb.movieapp.repository

import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.model.Either
import kz.moviedb.movieapp.model.SearchResponse

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */
interface MovieRepository {
    suspend fun getMovieBySearch(text: String) : Either<SearchResponse>
    suspend fun getMovieById(id: String) : Either<MovieResponse>
}