package kz.moviedb.lab1.model

/**
 * Created by Sarsenov Yerlan on 13.01.2021.
 */
sealed class SealedResponse<out T: Any?> {
    data class ResponseSuccess<out T: Any>(val response: T): SealedResponse<T>()
    data class ResponseError(val error: String): SealedResponse<Nothing>()
}