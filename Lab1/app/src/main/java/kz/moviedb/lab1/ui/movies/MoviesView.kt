package kz.moviedb.lab1.ui.movies

import kz.moviedb.lab1.model.MovieResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

/**
 * Created by Sarsenov Yerlan on 24.12.2020.
 */
interface MoviesView : MvpView {

    @Skip
    fun showLoading()

    @Skip
    fun hideLoading()

    @Skip
    fun openMovieDetail(movieResponse: MovieResponse)

    @Skip
    fun showError(error: String)

}