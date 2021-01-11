package kz.moviedb.movieapp

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.*
import kz.moviedb.movieapp.utils.work_manager.CITATION_DOWNLOADING_WORK
import kz.moviedb.movieapp.utils.work_manager.CITATION_DOWNLOADING_WORK_INIT
import kz.moviedb.movieapp.utils.work_manager.CitationWorker
import kz.moviedb.movieapp.utils.work_manager.MyObserver
import java.util.concurrent.TimeUnit

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyObserver(applicationContext))
        startCitationWorker()
    }

    private fun startCitationWorker() {
        val manager = WorkManager.getInstance(applicationContext)
        val periodicWorkRequest = PeriodicWorkRequestBuilder<CitationWorker>(
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS,
            PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS,
            TimeUnit.MILLISECONDS
        ).addTag(CITATION_DOWNLOADING_WORK).build()
        val initWorkRequest = OneTimeWorkRequestBuilder<CitationWorker>().addTag(
            CITATION_DOWNLOADING_WORK_INIT).build()
        manager.beginUniqueWork(CITATION_DOWNLOADING_WORK_INIT,
            ExistingWorkPolicy.KEEP, initWorkRequest).enqueue()
        manager.enqueueUniquePeriodicWork(
            CITATION_DOWNLOADING_WORK, ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest
        )
    }

}