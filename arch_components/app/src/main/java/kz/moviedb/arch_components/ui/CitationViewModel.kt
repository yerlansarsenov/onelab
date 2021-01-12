package kz.moviedb.arch_components.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.moviedb.arch_components.apiUtils.ApiUtils
import kz.moviedb.arch_components.db.CitationDataBase
import kz.moviedb.arch_components.model.RoomCitation
import kz.moviedb.arch_components.workManager.FORMAT
import kz.moviedb.arch_components.workManager.METHOD

private val TAG = "ViewModel"

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */



class CitationViewModel(application: Application) : AndroidViewModel(application) {

    val liveDataCitation : LiveData<List<RoomCitation>> =
        CitationDataBase.getDB(getApplication()).citationDao().getAllCitationsLiveData()

//    fun getList() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                try {
//                    val response = ApiUtils.api().getCitation(method = METHOD, format =  FORMAT)
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            val citation = RoomCitation.convertToRoomEntity(it)
//                            CitationDataBase.getDB(getApplication()).citationDao().insertCitation(citation)
//                        }
//                    } else {
//                        Log.e(TAG, "getList // else: ${response.message()}")
//                    }
//                } catch (e: Exception) {
//                    Log.e(TAG, "getList // catch: ${e.message}")
//                }
//            }
//        }
//    }

}