package kz.moviedb.data.api

import kz.moviedb.data.model.MovieResponseData
import kz.moviedb.data.model.SearchResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */
interface MovieApi {
    @GET("/")
    suspend fun getMovieBySearch(@Query("s")text: String) : Response<SearchResponseData>

    @GET("/")
    suspend fun getMovieById(@Query("i") id: String) : Response<MovieResponseData>
}