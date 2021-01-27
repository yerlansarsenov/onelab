package kz.moviedb.presentation.di

import kz.moviedb.presentation.ui.detail.DetailViewModel
import kz.moviedb.presentation.ui.movies.MoviesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val viewModelModule = module {
    viewModel {
        MoviesViewModel(
            application = get(),
            searchUseCase = get(),
            citationUseCase = get()
        )
    }
    viewModel {
        DetailViewModel(
            useCase = get()
        )
    }
}