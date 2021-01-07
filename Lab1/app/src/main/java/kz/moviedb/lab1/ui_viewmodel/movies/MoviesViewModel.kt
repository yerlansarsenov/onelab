package kz.moviedb.lab1.ui_viewmodel.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.moviedb.lab1.api.ApiUtils
import kz.moviedb.lab1.model.MovieResponse

/**
 * Created by Sarsenov Yerlan on 07.01.2021.
 */
class MoviesViewModel : ViewModel() {

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
            try {
                val result = ApiUtils.api().getMovieById(id).await()
                withContext(Dispatchers.Main) {
                    _liveDataLoading.value = true
                    try {
                        _livaDdataMovie.value = result
                    } catch (e: Exception) {
                        Log.e("ViewModel", "searchMovieById: ${e.message}")
                        _liveDataHasInternetProblems.value = true
                    } finally {
                        _liveDataLoading.value = false
                    }
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "searchMovieById: ${e.message}")
                _liveDataHasInternetProblems.value = true
            }
        }
    }

}