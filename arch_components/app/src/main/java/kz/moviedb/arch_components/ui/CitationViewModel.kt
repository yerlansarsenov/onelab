package kz.moviedb.arch_components.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.moviedb.arch_components.MyApplication
import kz.moviedb.arch_components.apiUtils.ApiUtils
import kz.moviedb.arch_components.model.RoomCitation
import kz.moviedb.arch_components.workManager.FORMAT
import kz.moviedb.arch_components.workManager.METHOD

private val TAG = "ViewModel"

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */



class CitationViewModel(application: Application) : AndroidViewModel(application) {

//    private var _liveDataCitation = MutableLiveData<List<Citation>>()
//    val liveDataCitation : LiveData<List<Citation>>
//        get() = _liveDataCitation

    fun getList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = ApiUtils.api().getCitation(method = METHOD, format =  FORMAT)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val citation = RoomCitation.convertToRoomEntity(it)
                            (getApplication<MyApplication>()).dao.insertCitation(citation)
                        }
                    } else {
                        Log.e(TAG, "getList // else: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "getList // catch: ${e.message}")
                }
            }
        }
    }
    
}