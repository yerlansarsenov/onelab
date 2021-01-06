package kz.moviedb.lab1.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.moviedb.lab1.api.ApiUtils
import kz.moviedb.lab1.model.MovieResponse
import moxy.MvpPresenter
import moxy.presenterScope

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */

class MoviesPresenter : MvpPresenter<MoviesView>() {

    var _liveDataMovie = MutableLiveData<MovieResponse>()
    val liveDataMovie: LiveData<MovieResponse>
        get() = _liveDataMovie

    /*fun searchMovieById(id: String) {
        presenterScope.launch {
            withContext(Dispatchers.IO) {
                viewState.showLoading()
                try {
                    _liveDataMovie.postValue(ApiUtils.api().getMovieById(id).await())
                    liveDataMovie.value?.let {
                        if (it.Error === null || it.Error.isEmpty()) {
                            viewState.openMovieDetail(it)
                        } else {
                            viewState.showError(it.Error)
                        }
                    }
                } catch (e: Exception) {
                    Log.e(MoviesPresenter::javaClass.name, "searchMovieById: ${e.message}")
                } finally {
                    viewState.hideLoading()
                }
            }
        }
    }*/

    fun searchMovieById(id: String) {
        presenterScope.launch {
            try {
                val result = ApiUtils.api().getMovieById(id).await()
                withContext(Dispatchers.Main) {
                    viewState.showLoading()
                    try {
                        _liveDataMovie.value = result
                        liveDataMovie.value?.let {
                            if (it.Error === null || it.Error.isEmpty()) {
                                viewState.openMovieDetail(it)
                            } else {
                                viewState.showError(it.Error)
                            }
                        }
                    } catch (e: Exception) {
                        Log.e(MoviesPresenter::javaClass.name, "searchMovieById: ${e.message}")
                    } finally {
                        viewState.hideLoading()
                    }
                }
            } catch (e: Exception) {
                Log.e(MoviesPresenter::javaClass.name, "searchMovieById: ${e.message}")
                e.message?.let { viewState.showError(it) }
            }
        }
    }


}