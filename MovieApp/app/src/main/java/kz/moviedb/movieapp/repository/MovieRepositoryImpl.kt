package kz.moviedb.movieapp.repository

import android.accounts.NetworkErrorException
import kz.moviedb.movieapp.api.MovieApi
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.model.Either
import kz.moviedb.movieapp.model.SearchResponse

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

private const val RESPONSE_TRUE_VALUE = "True"


class MovieRepositoryImpl(val api: MovieApi) : MovieRepository {
    override suspend fun getMovieBySearch(text: String): Either<SearchResponse> {
        try {
            val response = api.getMovieBySearch(text)
            if (response.isSuccessful) {
                if (response.body() == null)
                    return Either.Error(response.message())
                if (response.body()!!.response == RESPONSE_TRUE_VALUE)
                    return Either.Success(response.body()!!)
                // response have error message if response != "True"
                return Either.Error(response.body()!!.error!!)
            }
            return Either.Error(response.message())
        } catch (e: NetworkErrorException) {
            return Either.Error(e.message.toString())
        } catch (e: Exception) {
            return Either.Error(e.message.toString())
        }
    }

    override suspend fun getMovieById(id: String): Either<MovieResponse> {
        try {
            val response = api.getMovieById(id)
            if (response.isSuccessful) {
                if (response.body() == null)
                    return Either.Error(response.message())
                if (response.body()!!.response == RESPONSE_TRUE_VALUE)
                    return Either.Success(response.body()!!)
                // response have error message if response != "True"
                return Either.Error(response.body()!!.error)
            }
            return Either.Error(response.message())
        } catch (e: NetworkErrorException) {
            return Either.Error(e.message.toString())
        } catch (e: Exception) {
            return Either.Error(e.message.toString())
        }
    }
}