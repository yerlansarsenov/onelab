package kz.moviedb.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.moviedb.movieapp.model.work_manager.RoomCitation

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
@Dao
interface CitationDao {
    @Query("select * from citations_table")
    suspend fun getAllCitations() : List<RoomCitation>

    @Query("select * from citations_table")
    fun getAllCitationsLiveData() : LiveData<List<RoomCitation>>

    @Query("select * from citations_table where id = (:mId)")
    suspend fun getCitationById(mId: Int) : RoomCitation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCitation(vararg citations: RoomCitation)
}