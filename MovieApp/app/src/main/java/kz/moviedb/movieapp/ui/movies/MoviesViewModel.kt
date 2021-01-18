package kz.moviedb.movieapp.ui.movies

import android.accounts.NetworkErrorException
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kz.moviedb.movieapp.api.ApiUtils
import kz.moviedb.movieapp.db.CitationDataBase
import kz.moviedb.movieapp.model.BaseListItem
import kz.moviedb.movieapp.model.BaseListItem.ErrorResponse
import kz.moviedb.movieapp.model.BaseListItem.Search
import kz.moviedb.movieapp.model.BaseListItem.RoomCitation

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
private const val TAG = "MoviesViewModel"

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private var _liveDataLoading = MutableLiveData<Boolean>(false)
    val liveDataLoading: LiveData<Boolean>
        get() = _liveDataLoading
    private var _liveDataHasInternetProblems = MutableLiveData<Boolean>(false)
    val liveDataHasInternetProblems: LiveData<Boolean>
        get() = _liveDataHasInternetProblems
    private var _liveDataBaseList = MutableLiveData<List<BaseListItem>>()
    val liveDataBaseList: LiveData<List<BaseListItem>>
        get() = _liveDataBaseList


    init {
        viewModelScope.launch { initCitations() }
    }

    private suspend fun initCitations() {
        Mutex().withLock {
            val list = CitationDataBase.getDB(getApplication()).citationDao().getAllCitations()
            if (list.isNotEmpty()) {
                _liveDataBaseList.value = list
            }
            Log.e(TAG, "initCitations: ")
        }
    }

    fun searchMoviesByName(name: String) {
        viewModelScope.launch {
            try {
                val result = ApiUtils.api_Movie().getMovieBySearch(name).await()
                withContext(Dispatchers.Main) {
                    _liveDataLoading.value = true
                    try {
                        if (result.error.isNullOrEmpty()) {
                            if (!_liveDataBaseList.value.isNullOrEmpty()) {
                                _liveDataBaseList.value = _liveDataBaseList.value!!.plus(result.search)
                                Log.e(TAG, "searchMoviesByName: so, the list size is ${_liveDataBaseList.value!!.size}")
                            } else {
                                _liveDataBaseList.value = result.search
                            }
                        } else {
                            val errorMessage = listOf(ErrorResponse(result.error))
                            _liveDataBaseList.value = errorMessage
                        }
                    } catch (e: NetworkErrorException) {
                        _liveDataHasInternetProblems.value = true
                        Log.e(TAG, "searchMoviesByName: catch1 ${e.message}")
                    } catch (e: Exception) {
                        Log.e(TAG, "searchMoviesByName: catch2 ${e.message}")
                    } finally {
                        _liveDataLoading.value = false
                    }
                }
            } catch (e: Exception) {
                _liveDataHasInternetProblems.value = true
                Log.e(TAG, "searchMoviesByName: catch3 ${e.message}")
            }
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}