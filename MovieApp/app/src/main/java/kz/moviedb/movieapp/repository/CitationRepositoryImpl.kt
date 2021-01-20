package kz.moviedb.movieapp.repository

import androidx.work.ListenableWorker
import kz.moviedb.movieapp.api.wrok_manager.CitationApi
import kz.moviedb.movieapp.db.CitationDataBase
import kz.moviedb.movieapp.model.BaseListItem
import kz.moviedb.movieapp.utils.work_manager.FORMAT
import kz.moviedb.movieapp.utils.work_manager.METHOD

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */
class CitationRepositoryImpl(private val api: CitationApi, private val dataBase: CitationDataBase): CitationRepository {
    override suspend fun getCitationFromInternet(): ListenableWorker.Result {
        return try {
            val response = api.getCitation(method = METHOD, format =  FORMAT)
            if (response.isSuccessful) {
                response.body()?.let {
                    val citation = BaseListItem.RoomCitation.convertToRoomEntity(it)
                    dataBase.citationDao().insertCitation(citation)
                }
                ListenableWorker.Result.success()
            } else
                ListenableWorker.Result.failure()
        } catch (e: Exception) {
            ListenableWorker.Result.failure()
        }
    }

    override suspend fun getAllCitationFromDatabase(): List<BaseListItem.RoomCitation> = dataBase.citationDao().getAllCitations()

}