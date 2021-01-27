package kz.moviedb.data.api.work_manager

import kz.moviedb.data.model.work_manager.JsonCitation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sarsenov Yerlan on 11.01.2021.
 */
interface CitationApi {

    @GET("1.0/")
    suspend fun getCitation(
        @Query("method") method: String,
        @Query("format") format: String) : Response<JsonCitation>

}