package kz.moviedb.lab1.ui.detail

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
interface DetailView : MvpView {

    @OneExecution
    fun decorateViews()

}