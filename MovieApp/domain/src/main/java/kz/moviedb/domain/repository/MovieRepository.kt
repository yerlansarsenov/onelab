package kz.moviedb.domain.repository

import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.model.MovieResponse


/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */
interface MovieRepository {
    suspend fun getMovieBySearch(text: String) : Either<List<BaseListItem.Search>>
    suspend fun getMovieById(id: String) : Either<MovieResponse>
}