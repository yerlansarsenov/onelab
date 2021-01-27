package kz.moviedb.presentation.ui.movies

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
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.usecase.CitationUseCase
import kz.moviedb.domain.usecase.SearchUseCase
import kz.moviedb.presentation.model.SearchState

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
private const val TAG = "MoviesViewModel"

class MoviesViewModel(
    application: Application,
    private val searchUseCase: SearchUseCase,
    private val citationUseCase: CitationUseCase
) : AndroidViewModel(application) {

    private val _liveDataState = MutableLiveData<SearchState>()
    val liveDataState: LiveData<SearchState>
        get() = _liveDataState


    fun searchMoviesByName(name: String) {
        viewModelScope.launch {
            _liveDataState.value = SearchState.ShowLoading
            val list = citationUseCase.getAllCitationFromDatabase()
            if (list.isNotEmpty()) {
                _liveDataState.value = SearchState.ResponseList(list)
            }
            when (val result = searchUseCase.getMovieBySearch(name)) {
                is Either.Success -> {
                    if (_liveDataState.value != null &&
                        _liveDataState.value is SearchState.ResponseList &&
                        !(_liveDataState.value as SearchState.ResponseList).list.isNullOrEmpty()) {
                        _liveDataState.value = SearchState.ResponseList((_liveDataState.value as SearchState.ResponseList).list.plus(result.response))
                    } else {
                        _liveDataState.value = SearchState.ResponseList(result.response)
                    }
                }
                is Either.Error -> {
                    _liveDataState.value = SearchState.Error(result.error)
                }
            }
            _liveDataState.value = SearchState.HideLoading
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}