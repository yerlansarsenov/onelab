package kz.moviedb.movieapp.moduls

import kz.moviedb.movieapp.ui.detail.DetailViewModel
import kz.moviedb.movieapp.ui.movies.MoviesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val viewModelModule = module {
    viewModel {
        MoviesViewModel(get(), get())
    }
    viewModel {
        DetailViewModel(get())
    }
}