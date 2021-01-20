package kz.moviedb.movieapp.moduls

import kz.moviedb.movieapp.repository.CitationRepository
import kz.moviedb.movieapp.repository.CitationRepositoryImpl
import kz.moviedb.movieapp.repository.MovieRepository
import kz.moviedb.movieapp.repository.MovieRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryImpl(get()) }
    factory<CitationRepository> { CitationRepositoryImpl(get(), get()) }
}