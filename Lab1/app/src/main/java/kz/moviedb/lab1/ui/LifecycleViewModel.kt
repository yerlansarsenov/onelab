package kz.moviedb.lab1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Sarsenov Yerlan on 06.01.2021.
 */
class LifecycleViewModel : ViewModel() {

    val liveData = MutableLiveData<String>()

}