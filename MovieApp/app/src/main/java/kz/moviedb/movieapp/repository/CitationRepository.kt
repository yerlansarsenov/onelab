package kz.moviedb.movieapp.repository

import androidx.work.ListenableWorker
import kz.moviedb.movieapp.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */
interface CitationRepository {
    suspend fun getCitationFromInternet(): ListenableWorker.Result

    suspend fun getAllCitationFromDatabase(): List<BaseListItem.RoomCitation>
}