package kz.moviedb.lab1.abstract

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Created by Sarsenov Yerlan on 24.12.2020.
 */
interface BaseView : MvpView {
    @AddToEndSingle
    fun showLoading()
    @AddToEndSingle
    fun hideLoading()
}