package kz.moviedb.presentation.di

import kz.moviedb.domain.usecase.CitationUseCase
import kz.moviedb.domain.usecase.MovieResponseUseCase
import kz.moviedb.domain.usecase.SearchUseCase
import org.koin.dsl.module

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */

val useCaseModule = module {
    factory { CitationUseCase(repository = get()) }
    factory { MovieResponseUseCase(repository = get()) }
    factory { SearchUseCase(repository = get()) }
}