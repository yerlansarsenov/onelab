package kz.moviedb.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.moviedb.data.model.BaseListItemData

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
@Dao
interface CitationDao {
    @Query("select * from citations_table")
    suspend fun getAllCitations() : List<BaseListItemData.RoomCitationData>

    @Query("select * from citations_table")
    fun getAllCitationsLiveData() : LiveData<List<BaseListItemData.RoomCitationData>>

    @Query("select * from citations_table where id = (:mId)")
    suspend fun getCitationById(mId: Int) : BaseListItemData.RoomCitationData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCitation(vararg citations: BaseListItemData.RoomCitationData)

    @Query("delete from citations_table where 1=1")
    suspend fun deleteCitations()
}