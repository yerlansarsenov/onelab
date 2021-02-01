package kz.moviedb.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.usecase.MovieResponseUseCase
import kz.moviedb.presentation.model.LoadingState
import kz.moviedb.presentation.model.MovieState

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
class DetailViewModel(private val useCase: MovieResponseUseCase) : ViewModel() {

    private val _livaDataMovie = MutableLiveData<MovieState>()
    val liveDataMovie: LiveData<MovieState>
        get() = _livaDataMovie

    private val _liveDataLoadingState = MutableLiveData<LoadingState>()
    val liveDataLoadingState: LiveData<LoadingState>
        get() = _liveDataLoadingState


    fun searchMovieById(id: String) {
        if (_livaDataMovie.value != null && _liveDataLoadingState.value != null)
            return
        viewModelScope.launch {
            _liveDataLoadingState.value = LoadingState.ShowLoading
            when (val result = useCase.getMovieById(id)) {
                is Either.Success -> {
                    _livaDataMovie.value = MovieState.Response(result.response)
                }
                is Either.Error -> {
                    _livaDataMovie.value = MovieState.Error(result.error)
                }
            }
            _liveDataLoadingState.value = LoadingState.HideLoading
        }
    }

//    override fun onCleared() {
//        viewModelScope.cancel()
//        super.onCleared()
//    }

}