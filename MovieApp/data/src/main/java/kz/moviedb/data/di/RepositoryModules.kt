package kz.moviedb.data.di

import kz.moviedb.data.repository.CitationRepositoryImpl
import kz.moviedb.data.repository.MovieRepositoryImpl
import kz.moviedb.domain.repository.CitationRepository
import kz.moviedb.domain.repository.MovieRepository
import org.koin.dsl.module

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            api = get(),
            mapperMovie = get(),
            mapperSearch = get()
        )
    }
    single<CitationRepository> {
        CitationRepositoryImpl(
            api = get(),
            dao = get(),
            mapper = get()
        )
    }
}