package kz.moviedb.movieapp.utils.work_manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kz.moviedb.movieapp.api.ApiUtils
import kz.moviedb.movieapp.db.CitationDataBase
import kz.moviedb.movieapp.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 08.01.2021.
 */

const val METHOD = "getQuote"
const val FORMAT = "json"

class CitationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = try {
        val response = ApiUtils.api_Citation().getCitation(method = METHOD, format =  FORMAT)
        if (response.isSuccessful) {
            response.body()?.let {
                val citation = BaseListItem.RoomCitation.convertToRoomEntity(it)
                CitationDataBase.getDB(applicationContext).citationDao().insertCitation(citation)
            }
            Result.success()
        } else
            Result.failure()
    } catch (e: Exception) {
        Result.failure()
    }
}