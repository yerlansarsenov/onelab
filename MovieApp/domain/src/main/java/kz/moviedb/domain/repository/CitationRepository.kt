package kz.moviedb.domain.repository

import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.domain.model.Either

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */
interface CitationRepository {
    suspend fun getCitationFromInternet(): Either<BaseListItem.RoomCitation>

    suspend fun getAllCitationFromDatabase(): List<BaseListItem.RoomCitation>
}