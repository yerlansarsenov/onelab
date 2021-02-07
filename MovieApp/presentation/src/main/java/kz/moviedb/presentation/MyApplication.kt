package kz.moviedb.presentation

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.*
import kz.moviedb.data.di.*
import kz.moviedb.presentation.di.*
import kz.moviedb.presentation.utils.work_manager.CITATION_DOWNLOADING_WORK
import kz.moviedb.presentation.utils.work_manager.CITATION_DOWNLOADING_WORK_INIT
import kz.moviedb.presentation.utils.work_manager.CitationWorker
import kz.moviedb.presentation.utils.work_manager.MyObserver
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.concurrent.TimeUnit

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(
                listOf(
                    mapperModule,
                    retrofitCitationModule,
                    retrofitMovieModule,
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
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
        manager.let {
            it.beginUniqueWork(CITATION_DOWNLOADING_WORK_INIT,
                ExistingWorkPolicy.KEEP, initWorkRequest).enqueue()
            it.enqueueUniquePeriodicWork(
                CITATION_DOWNLOADING_WORK, ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest
            )
        }
    }

}