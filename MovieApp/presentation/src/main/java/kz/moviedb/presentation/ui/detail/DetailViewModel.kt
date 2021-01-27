package kz.moviedb.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.usecase.MovieResponseUseCase
import kz.moviedb.presentation.model.MovieState

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
class DetailViewModel(private val useCase: MovieResponseUseCase) : ViewModel() {

    private val _livaDataMovie = MutableLiveData<MovieState>()
    val liveDataMovie: LiveData<MovieState>
        get() = _livaDataMovie


    fun searchMovieById(id: String) {
        viewModelScope.launch {
            _livaDataMovie.value = MovieState.ShowLoading
            when (val result = useCase.getMovieById(id)) {
                is Either.Success -> {
                    _livaDataMovie.value = MovieState.Response(result.response)
                }
                is Either.Error -> {
                    _livaDataMovie.value = MovieState.Error(result.error)
                }
            }
            _livaDataMovie.value = MovieState.HideLoading
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}