package kz.moviedb.movieapp.api

import kotlinx.coroutines.Deferred
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
interface MovieApi {
    @GET("/")
    suspend fun getMovieBySearch(@Query("s")text: String) : Response<SearchResponse>

    @GET("/")
    suspend fun getMovieById(@Query("i") id: String) : Response<MovieResponse>
}