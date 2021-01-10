package kz.moviedb.arch_components.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kz.moviedb.arch_components.apiUtils.ApiUtils
import kz.moviedb.arch_components.db.CitationDataBase
import kz.moviedb.arch_components.model.RoomCitation

/**
 * Created by Sarsenov Yerlan on 08.01.2021.
 */

const val METHOD = "getQuote"
const val FORMAT = "json"

class CitationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = try {
        val response = ApiUtils.api().getCitation(method = METHOD, format =  FORMAT)
        if (response.isSuccessful) {
            response.body()?.let {
                val citation = RoomCitation.convertToRoomEntity(it)
                CitationDataBase.getDB(applicationContext).citationDao().insertCitation(citation)
            }
            Result.success()
        } else
            Result.failure()
    } catch (e: Exception) {
        Result.failure()
    }
}