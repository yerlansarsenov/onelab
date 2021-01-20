package kz.moviedb.movieapp.ui.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kz.moviedb.movieapp.model.BaseListItem
import kz.moviedb.movieapp.model.BaseListItem.ErrorResponse
import kz.moviedb.movieapp.model.Either
import kz.moviedb.movieapp.repository.CitationRepository
import kz.moviedb.movieapp.repository.MovieRepository
import org.koin.java.KoinJavaComponent.inject

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
private const val TAG = "MoviesViewModel"

class MoviesViewModel(application: Application, private val repository: MovieRepository) : AndroidViewModel(application) {
    private var _liveDataLoading = MutableLiveData<Boolean>(false)
    val liveDataLoading: LiveData<Boolean>
        get() = _liveDataLoading
    private var _liveDataHasInternetProblems = MutableLiveData<Boolean>(false)
    val liveDataHasInternetProblems: LiveData<Boolean>
        get() = _liveDataHasInternetProblems
    private var _liveDataBaseList = MutableLiveData<List<BaseListItem>>()
    val liveDataBaseList: LiveData<List<BaseListItem>>
        get() = _liveDataBaseList

    private val citationRepositoryImpl by inject(CitationRepository::class.java)

    init {
        viewModelScope.launch { initCitations() }
    }

    private suspend fun initCitations() {
        Mutex().withLock {
            val list = citationRepositoryImpl.getAllCitationFromDatabase()
            if (list.isNotEmpty()) {
                _liveDataBaseList.value = list
            }
            Log.e(TAG, "initCitations: ")
        }
    }

    fun searchMoviesByName(name: String) {
        viewModelScope.launch {
            _liveDataLoading.value = true
            when (val result = repository.getMovieBySearch(name)) {
                is Either.Success -> {
                    if (!_liveDataBaseList.value.isNullOrEmpty()) {
                        _liveDataBaseList.value = _liveDataBaseList.value!!.plus(result.response.search)
                    } else {
                        _liveDataBaseList.value = result.response.search
                    }
                }
                is Either.Error -> {
                    val errorMessage = listOf(ErrorResponse(result.error))
                    _liveDataBaseList.value = errorMessage
                }
            }
            _liveDataLoading.value = false
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}