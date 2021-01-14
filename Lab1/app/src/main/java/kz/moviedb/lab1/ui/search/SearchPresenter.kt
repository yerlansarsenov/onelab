package kz.moviedb.lab1.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kz.moviedb.lab1.model.SealedResponse
import kz.moviedb.lab1.model.SearchResponseState
import kz.moviedb.lab1.repository.MovieRepositoryImpl
import moxy.MvpPresenter
import moxy.presenterScope

/**
 * Created by Sarsenov Yerlan on 23.12.2020.
 */

//@InjectViewState
class SearchPresenter(val repository: MovieRepositoryImpl) : MvpPresenter<SearchView>() {

    var _liveDataSearchResponse = MutableLiveData<SearchResponseState>()
    val liveDataSearchResponse: LiveData<SearchResponseState>
        get() = _liveDataSearchResponse

    /**
     * i recognized that i dont need livedata here
     *
     */

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
            when (val response = repository.getMovieBySearch(name)) {
                is SealedResponse.ResponseSuccess -> {
                    _liveDataSearchResponse.value = SearchResponseState.SearchResult(response.response)
                    viewState.openMovies(response.response.Search)
                }
                is SealedResponse.ResponseError -> {
                    _liveDataSearchResponse.value = SearchResponseState.Error(response.error)
                    viewState.openError(response.error)
                }
            }
            /*try {
                //val result = ApiUtils.api().getMovieBySearch(name)//.await()
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
            }*/
        }
    }

}