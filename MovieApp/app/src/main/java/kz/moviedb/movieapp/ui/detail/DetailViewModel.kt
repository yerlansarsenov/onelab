package kz.moviedb.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kz.moviedb.movieapp.model.Either
import kz.moviedb.movieapp.model.MovieResponse
import kz.moviedb.movieapp.repository.MovieRepository

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _livaDdataMovie = MutableLiveData<MovieResponse>()
    val liveDataMovie: LiveData<MovieResponse>
        get() = _livaDdataMovie
    private var _liveDataLoading = MutableLiveData<Boolean>(false)
    val liveDataLoading: LiveData<Boolean>
        get() = _liveDataLoading
    private var _liveDataHasInternetProblems = MutableLiveData<Boolean>(false)
    val liveDataHasInternetProblems: LiveData<Boolean>
        get() = _liveDataHasInternetProblems


    fun searchMovieById(id: String) {
        viewModelScope.launch {
            _liveDataLoading.value = true
            when (val result = repository.getMovieById(id)) {
                is Either.Success -> {
                    _livaDdataMovie.value = result.response
                }
                is Either.Error -> {
                    _liveDataHasInternetProblems.value = true
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