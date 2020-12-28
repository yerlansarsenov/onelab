package kz.moviedb.lab1.ui.detail

import moxy.MvpPresenter

/**
 * Created by Sarsenov Yerlan on 25.12.2020.
 */
class DetailPresenter : MvpPresenter<DetailView>() {
    fun decorateView() {
        viewState.decorateViews()
    }

}