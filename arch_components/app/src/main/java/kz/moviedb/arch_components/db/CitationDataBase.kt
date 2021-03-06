package kz.moviedb.arch_components.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.moviedb.arch_components.model.RoomCitation

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
@Database(entities = [RoomCitation::class], version = 1)
abstract class CitationDataBase : RoomDatabase() {
    abstract fun citationDao() : CitationDao

    companion object {
        private var INSTANCE: CitationDataBase? = null

        fun getDB(context: Context): CitationDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    CitationDataBase::class.java, "citations"
                ).build()
            }
            return INSTANCE as CitationDataBase
        }
    }
}