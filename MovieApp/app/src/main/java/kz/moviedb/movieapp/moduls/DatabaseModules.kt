package kz.moviedb.movieapp.moduls

import android.content.Context
import androidx.room.Room
import kz.moviedb.movieapp.db.CitationDataBase
import org.koin.dsl.module

private const val CITATION_DB = "citations"

/**
 * Created by Sarsenov Yerlan on 20.01.2021.
 */

val databaseModule = module {
    fun getDB(context: Context): CitationDataBase {
        return Room.databaseBuilder(
                context,
                CitationDataBase::class.java, CITATION_DB
        ).build()
    }
    single { getDB(get()) }
}