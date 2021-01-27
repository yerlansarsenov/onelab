package kz.moviedb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.moviedb.data.model.BaseListItemData

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
@Database(entities = [BaseListItemData.RoomCitationData::class], version = 1)
abstract class CitationDataBase : RoomDatabase() {
    abstract fun citationDao() : CitationDao
}