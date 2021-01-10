package kz.moviedb.arch_components.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.moviedb.arch_components.model.RoomCitation

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
@Database(entities = [RoomCitation::class], version = 1)
abstract class CitationDataBase : RoomDatabase() {
    abstract fun citationDao() : CitationDao
}