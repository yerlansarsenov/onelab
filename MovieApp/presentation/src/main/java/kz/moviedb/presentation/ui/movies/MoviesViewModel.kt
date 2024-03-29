package kz.moviedb.presentation.ui.movies

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.usecase.CitationUseCase
import kz.moviedb.domain.usecase.SearchUseCase
import kz.moviedb.presentation.model.LoadingState
import kz.moviedb.presentation.model.SearchState

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
private const val TAG = "MoviesViewModel"

class MoviesViewModel(
    private val searchUseCase: SearchUseCase,
    private val citationUseCase: CitationUseCase
) : ViewModel() {

    private val _liveDataState = MutableLiveData<SearchState>()
    val liveDataState: LiveData<SearchState>
        get() = _liveDataState

    private val _liveDataCitation = citationUseCase.getAllCitationFromDatabaseFlow().asLiveData()
    val liveDataCitation: LiveData<List<BaseListItem.RoomCitation>>
        get() = _liveDataCitation

    private val _liveDataLoadingState = MutableLiveData<LoadingState>()
    val liveDataLoadingState: LiveData<LoadingState>
        get() = _liveDataLoadingState

    fun searchMoviesByName(name: String) {
        if (_liveDataState.value != null && _liveDataLoadingState.value != null) return
        viewModelScope.launch {
            _liveDataLoadingState.value = LoadingState.ShowLoading
            /*val list = citationUseCase.getAllCitationFromDatabase()
            if (list.isNotEmpty()) {
                _liveDataState.value = SearchState.ResponseList(list)
            }*/
            when (val result = searchUseCase.getMovieBySearch(name)) {
                is Either.Success -> {
                    if (isAlreadyHaveItems()) {
                        _liveDataState.value = SearchState.ResponseList((_liveDataState.value as SearchState.ResponseList).list.plus(result.response))
                    } else {
                        _liveDataState.value = SearchState.ResponseList(result.response)
                    }
                }
                is Either.Error -> {
                    _liveDataState.value = SearchState.Error(result.error)
                }
            }
            _liveDataLoadingState.value = LoadingState.HideLoading
        }
    }

    private fun isAlreadyHaveItems() = _liveDataState.value != null &&
            _liveDataState.value is SearchState.ResponseList &&
            !(_liveDataState.value as SearchState.ResponseList).list.isNullOrEmpty()


}