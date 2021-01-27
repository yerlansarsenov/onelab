package kz.moviedb.domain.mapper

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
interface Mapper<F, S> {
    fun convert(model: F): S
    fun invert(model: S): F
}