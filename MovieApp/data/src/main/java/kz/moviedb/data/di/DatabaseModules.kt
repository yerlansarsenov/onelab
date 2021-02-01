package kz.moviedb.data.di

import android.content.Context
import androidx.room.Room
import kz.moviedb.data.database.CitationDao
import kz.moviedb.data.database.CitationDataBase
import org.koin.dsl.module

private const val CITATION_DB = "citations"

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val databaseModule = module {
    fun getDatabaseDao(context: Context): CitationDao {
        return Room.databaseBuilder(
                context,
                CitationDataBase::class.java, CITATION_DB
        ).build().citationDao()
    }
    single { getDatabaseDao(get()) }
}