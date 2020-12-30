package kz.moviedb.lab1.ui.search

import kz.moviedb.lab1.model.Search
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

/**
 * Created by Sarsenov Yerlan on 24.12.2020.
 */
interface SearchView : MvpView {
    //@AddToEndSingle
    @Skip
    fun showLoading()
    //@AddToEndSingle
    @Skip
    fun hideLoading()
    //@AddToEndSingle
    @Skip
    fun openMovies(list: List<Search>)
    //@AddToEndSingle
    @Skip
    fun openError(error: String)
}