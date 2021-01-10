package kz.moviedb.arch_components

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.room.Room
import androidx.work.*
import kz.moviedb.arch_components.db.CitationDao
import kz.moviedb.arch_components.db.CitationDataBase
import kz.moviedb.arch_components.workManager.CITATION_DOWNLOADING_WORK
import kz.moviedb.arch_components.workManager.CITATION_DOWNLOADING_WORK_INIT
import kz.moviedb.arch_components.workManager.CitationWorker
import kz.moviedb.arch_components.workManager.MyObserver
import java.util.concurrent.TimeUnit

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
class MyApplication : Application() {

    lateinit var citationDao: CitationDao

    val dao: CitationDao
        get() = citationDao

    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder(
            applicationContext,
            CitationDataBase::class.java, "citations"
        ).build()
        citationDao = db.citationDao()
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyObserver(applicationContext))
        startCitationWorker()
    }

    private fun startCitationWorker() {
        val periodicWorkRequest = PeriodicWorkRequestBuilder<CitationWorker>(
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.SECONDS,
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MINUTES
        ).addTag(CITATION_DOWNLOADING_WORK).build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            CITATION_DOWNLOADING_WORK, ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest
        )
    }

}