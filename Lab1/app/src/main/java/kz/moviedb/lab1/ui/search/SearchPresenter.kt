package kz.moviedb.lab1.ui.search

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.moviedb.lab1.api.ApiUtils
import kz.moviedb.lab1.model.SearchResponse
import moxy.MvpPresenter
import moxy.presenterScope

/**
 * Created by Sarsenov Yerlan on 23.12.2020.
 */

//@InjectViewState
class SearchPresenter: MvpPresenter<SearchView>() {

    var _liveDataSearchResponse = MutableLiveData<SearchResponse>()
    val liveDataSearchResponse: LiveData<SearchResponse>
        get() = _liveDataSearchResponse

    /*fun searchMoviesByName(name: String) {
        presenterScope.launch {
            withContext(Dispatchers.IO) {
                viewState.showLoading()
                try {
                    _liveDataSearchResponse.postValue(ApiUtils.api().getMovieBySearch(text = name).await())
                    try {
                        Log.e("TAg", "onViewCreated: " + (liveDataSearchResponse.value?.Search?.get(0)?.Title))
                        liveDataSearchResponse.value?.let {
                            viewState.openMovies(it.Search)
                        }
                    } catch (e: NullPointerException) {
                        Log.e("NullPointer", "onViewCreated: " + e.message)
                        try {
                            liveDataSearchResponse.value?.let { viewState.openError(it.Error) }
                        } catch (e: Exception) {
                            Log.e("Exception", "onViewCreated: " + e.message)
                        }
                    }
                } catch (e: NetworkErrorException) {
                    Log.e(SearchPresenter::javaClass.name, "searchMoviesByName: ${e.message}")
                    viewState.openError("No internet connection")
                } catch (e: Exception) {
                    Log.e(SearchPresenter::javaClass.name, "searchMoviesByName: ${e.message}")
                } finally {
                    viewState.hideLoading()
                }
            }
        }
    }*/

    fun searchMoviesByName(name: String) {
        presenterScope.launch {
            try {
                val result = ApiUtils.api().getMovieBySearch(name).await()
                withContext(Dispatchers.Main) {
                    viewState.showLoading()
                    try {
                        _liveDataSearchResponse.value = result
                        try {
                            liveDataSearchResponse.value?.let {
                                viewState.openMovies(it.Search)
                            }
                        } catch (e: NullPointerException) {
                            Log.e("NullPointer", "onViewCreated: " + e.message)
                            try {
                                liveDataSearchResponse.value?.let { viewState.openError(it.Error) }
                            } catch (e: Exception) {
                                Log.e("Exception", "onViewCreated: " + e.message)
                            }
                        }
                    } catch (e: NetworkErrorException) {
                        Log.e(SearchPresenter::javaClass.name, "searchMoviesByName: ${e.message}")
                        viewState.openError("No internet connection")
                    } catch (e: Exception) {
                        Log.e(SearchPresenter::javaClass.name, "searchMoviesByName: ${e.message}")
                    } finally {
                        viewState.hideLoading()
                    }
                }
            } catch (e: Exception) {
                Log.e(SearchPresenter::javaClass.name, "searchMoviesByName: ${e.message}")
                viewState.openError("No internet connection")
            }
        }
    }

}