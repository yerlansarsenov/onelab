package kz.moviedb.movieapp.ui.work_manager_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kz.moviedb.movieapp.db.CitationDataBase
import kz.moviedb.movieapp.model.work_manager.RoomCitation

/**
 * Created by Sarsenov Yerlan on 12.01.2021.
 */
class CitationViewModel(application: Application) : AndroidViewModel(application) {
    val liveDataCitation : LiveData<List<RoomCitation>> =
        CitationDataBase.getDB(getApplication()).citationDao().getAllCitationsLiveData()
}