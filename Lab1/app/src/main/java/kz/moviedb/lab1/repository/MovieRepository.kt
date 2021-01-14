package kz.moviedb.lab1.repository

import kotlinx.coroutines.Deferred
import kz.moviedb.lab1.model.MovieResponse
import kz.moviedb.lab1.model.SealedResponse
import kz.moviedb.lab1.model.SearchResponse

/**
 * Created by Sarsenov Yerlan on 13.01.2021.
 */
interface MovieRepository {
    suspend fun getMovieBySearch(text: String) : SealedResponse<SearchResponse>

    suspend fun getMovieById(id: String) : SealedResponse<MovieResponse>
}