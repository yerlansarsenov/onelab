package kz.moviedb.arch_components.apiUtils

import kz.moviedb.arch_components.model.JsonCitation
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Sarsenov Yerlan on 08.01.2021.
 */
interface MyApi {
    @GET("1.0/")
    suspend fun getCitation(
        @Query("method") method: String,
        @Query("format") format: String) : Response<JsonCitation>
}