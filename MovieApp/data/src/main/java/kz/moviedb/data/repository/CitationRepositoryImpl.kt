package kz.moviedb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.moviedb.data.FORMAT
import kz.moviedb.data.METHOD
import kz.moviedb.data.api.work_manager.CitationApi
import kz.moviedb.data.database.CitationDao
import kz.moviedb.data.mapper.CitationMapper
import kz.moviedb.data.model.BaseListItemData
import kz.moviedb.domain.model.BaseListItem
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.repository.CitationRepository


/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */
class CitationRepositoryImpl(
    private val api: CitationApi,
    private val dao: CitationDao,
    private val mapper: CitationMapper
    ):
    CitationRepository {
    override suspend fun getCitationFromInternet(): Either<BaseListItem.RoomCitation> {
        return try {
            val response = api.getCitation(METHOD, FORMAT)
            if (response.isSuccessful) {
                if (response.body() == null)
                    Either.Error(response.message())
                val citation = response.body()!!.let {
                    BaseListItemData.RoomCitationData.convertToRoomEntity(it)
                }
                dao.deleteCitations()
                dao.insertCitation(citation)
                Either.Success(mapper.convert(citation))
            } else {
                Either.Error(response.message())
            }
        } catch (e: Exception) {
            Either.Error(e.message.toString())
        }
    }

    override suspend fun getAllCitationFromDatabase(): List<BaseListItem.RoomCitation> {
        return dao.getAllCitations().map {
            mapper.convert(it)
        }
    }

    override fun getAllCitationFromDatabaseFlow(): Flow<List<BaseListItem.RoomCitation>> {
        return dao.getAllCitationsFlow().map { list ->
            list.map { item ->
                mapper.convert(item)
            }
        }
    }

}