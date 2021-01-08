package kz.moviedb.movieapp.ui.movies

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.moviedb.movieapp.api.ApiUtils
import kz.moviedb.movieapp.model.SearchResponse

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
class MoviesViewModel : ViewModel() {
    private var _liveDataSearchResponse = MutableLiveData<SearchResponse>()
    val liveDataSearchResponse: LiveData<SearchResponse>
        get() = _liveDataSearchResponse
    private var _liveDataLoading = MutableLiveData<Boolean>(false)
    val liveDataLoading: LiveData<Boolean>
        get() = _liveDataLoading
    private var _liveDataHasInternetProblems = MutableLiveData<Boolean>(false)
    val liveDataHasInternetProblems: LiveData<Boolean>
        get() = _liveDataHasInternetProblems

    fun searchMoviesByName(name: String) {
        viewModelScope.launch {
            try {
                val result = ApiUtils.api().getMovieBySearch(name).await()
                withContext(Dispatchers.Main) {
                    _liveDataLoading.value = true
                    try {
                        _liveDataSearchResponse.value = result
                        Log.e("ViewModel", "searchMoviesByName: result")
                    } catch (e: NetworkErrorException) {
                        _liveDataHasInternetProblems.value = true
                        Log.e("ViewModel", "searchMoviesByName: catch1 ${e.message}")
                    } catch (e: Exception) {
                        Log.e("ViewModel", "searchMoviesByName: catch2 ${e.message}")
                    } finally {
                        _liveDataLoading.value = false
                    }
                }
            } catch (e: Exception) {
                _liveDataHasInternetProblems.value = true
                Log.e("ViewModel", "searchMoviesByName: catch3 ${e.message}")
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}