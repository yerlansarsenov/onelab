package kz.moviedb.lab1.repository

import kz.moviedb.lab1.api.ApiUtils
import kz.moviedb.lab1.api.MovieAPI
import kz.moviedb.lab1.model.MovieResponse
import kz.moviedb.lab1.model.SealedResponse
import kz.moviedb.lab1.model.SearchResponse

private val RESPONSE_TRUE_VALUE = "True"

/**
 * Created by Sarsenov Yerlan on 13.01.2021.
 */
class MovieRepositoryImpl(val api: MovieAPI) : MovieRepository {
    override suspend fun getMovieBySearch(text: String): SealedResponse<SearchResponse> = try {
            val response = api.getMovieBySearch(text)
            if (response.isSuccessful) {
                if (response.body()?.Response == RESPONSE_TRUE_VALUE) {
                    SealedResponse.ResponseSuccess(response.body()!!)
                }
                SealedResponse.ResponseError(response.body()!!.Error)
            } else {
                SealedResponse.ResponseError(response.message())
            }
        } catch (e: Exception) {
            SealedResponse.ResponseError(e.message.toString())
        }


    override suspend fun getMovieById(id: String): SealedResponse<MovieResponse> = try {
        val response = api.getMovieById(id)
        if (response.isSuccessful) {
            if (response.body()?.Response == RESPONSE_TRUE_VALUE) {
                SealedResponse.ResponseSuccess(response.body()!!)
            }
            SealedResponse.ResponseError(response.body()!!.Error)
        } else {
            SealedResponse.ResponseError(response.message())
        }
    } catch (e: Exception) {
        SealedResponse.ResponseError(e.message.toString())
    }

}