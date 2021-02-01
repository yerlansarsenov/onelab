package kz.moviedb.domain.mapper

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
interface Mapper<S, F> {
    fun convert(model: S): F
}