package kz.moviedb.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.moviedb.movieapp.model.BaseListItem

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
@Dao
interface CitationDao {
    @Query("select * from citations_table")
    suspend fun getAllCitations() : List<BaseListItem.RoomCitation>

    @Query("select * from citations_table")
    fun getAllCitationsLiveData() : LiveData<List<BaseListItem.RoomCitation>>

    @Query("select * from citations_table where id = (:mId)")
    suspend fun getCitationById(mId: Int) : BaseListItem.RoomCitation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCitation(vararg citations: BaseListItem.RoomCitation)

    @Query("delete from citations_table where 1=1")
    suspend fun deleteCitations()
}