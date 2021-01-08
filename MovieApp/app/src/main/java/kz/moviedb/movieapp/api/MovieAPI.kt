package kz.moviedb.movieapp.api

import kotlinx.coroutines.Deferred
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
interface MovieAPI {
    @GET("/")
    fun getMovieBySearch(@Query("s")text: String) : Deferred<SearchResponse>

    @GET("/")
    fun getMovieById(@Query("i") id: String) : Deferred<MovieResponse>
}