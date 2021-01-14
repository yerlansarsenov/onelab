package kz.moviedb.lab1.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kz.moviedb.lab1.model.MovieResponseState
import kz.moviedb.lab1.model.SealedResponse
import kz.moviedb.lab1.repository.MovieRepositoryImpl
import moxy.MvpPresenter
import moxy.presenterScope

/**
 * Created by Sarsenov Yerlan on 21.12.2020.
 */

class MoviesPresenter(val repository: MovieRepositoryImpl) : MvpPresenter<MoviesView>() {

    var _liveDataMovie = MutableLiveData<MovieResponseState>()
    val liveDataMovie: LiveData<MovieResponseState>
        get() = _liveDataMovie

    /**
     * i recognized that i dont need livedata here
     *
     */

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
            when (val response = repository.getMovieById(id)) {
                is SealedResponse.ResponseSuccess -> {
                    _liveDataMovie.value = MovieResponseState.SearchResult(response.response)
                    viewState.openMovieDetail(response.response)
                }
                is SealedResponse.ResponseError -> {
                    _liveDataMovie.value = MovieResponseState.Error(response.error)
                    viewState.showError(response.error)
                }
            }
            /*try {
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
            }*/
        }
    }


}