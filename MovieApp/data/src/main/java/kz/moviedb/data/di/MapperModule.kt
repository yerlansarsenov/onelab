package kz.moviedb.data.di

import kz.moviedb.data.mapper.CitationMapper
import kz.moviedb.data.mapper.MovieMapper
import kz.moviedb.data.mapper.RatingMapper
import kz.moviedb.data.mapper.SearchMapper
import org.koin.dsl.module

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */

val mapperModule = module {
    factory { RatingMapper() }
    factory { CitationMapper() }
    factory { MovieMapper(ratingMapper = get()) }
    factory { SearchMapper() }
}