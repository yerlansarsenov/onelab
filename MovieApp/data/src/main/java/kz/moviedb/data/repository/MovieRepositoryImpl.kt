package kz.moviedb.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import kz.moviedb.data.api.MovieApi
import kz.moviedb.data.mapper.MovieMapper
import kz.moviedb.data.mapper.SearchMapper
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.model.MovieResponse
import kz.moviedb.domain.repository.MovieRepository

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

private const val RESPONSE_TRUE_VALUE = "True"


private const val TAG = "MovieRepositoryImpl"


class MovieRepositoryImpl(
    private val api: MovieApi,
    private val mapperSearch: SearchMapper,
    private val mapperMovie: MovieMapper
    ) : MovieRepository {
    override suspend fun getMovieBySearch(text: String): Either<List<BaseListItem.Search>> {
        try {
            val response = api.getMovieBySearch(text)
            if (response.isSuccessful) {
                if (response.body() == null)
                    return Either.Error(response.message())
                if (response.body()!!.response == RESPONSE_TRUE_VALUE) {
                    val list = response.body()!!.search.map {
                        mapperSearch.convert(it)
                    }
                    return Either.Success(list)
                }
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
                if (response.body() != null && response.body()!!.response == RESPONSE_TRUE_VALUE) {
                    Log.e(TAG, "getMovieById: ${response.body()!!.title}")
                    return Either.Success(mapperMovie.convert(response.body()!!))
                }
                // response have error message if response != "True"
                return Either.Error(response.body()!!.error!!)
            }
            return Either.Error(response.message())
        } catch (e: NetworkErrorException) {
            Log.e(TAG, "getMovieById: 1 catch: ${e.message.toString()}")
            return Either.Error(e.message.toString())
        } catch (e: Exception) {
            Log.e(TAG, "getMovieById: 2 catch: ${e.message.toString()}")
            return Either.Error(e.message.toString())
        }
    }
}