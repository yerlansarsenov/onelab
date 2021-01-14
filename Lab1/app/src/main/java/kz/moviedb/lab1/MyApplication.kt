package kz.moviedb.lab1

import android.app.Application
import kz.moviedb.lab1.koin_modules.apiModule
import kz.moviedb.lab1.koin_modules.presenterModule
import kz.moviedb.lab1.koin_modules.repositoryModule
import kz.moviedb.lab1.koin_modules.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Sarsenov Yerlan on 14.01.2021.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(listOf(
                repositoryModule,
                retrofitModule,
                apiModule,
                presenterModule
            ))
        }
    }
}