package kz.moviedb.movieapp.utils.work_manager

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.work.WorkManager

/**
 * Created by Sarsenov Yerlan on 09.01.2021.
 */
const val CITATION_DOWNLOADING_WORK = "CITATION_DOWNLOADING_WORK"

const val CITATION_DOWNLOADING_WORK_INIT = "CITATION_DOWNLOADING_WORK_INIT"

class MyObserver(private val context: Context) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        WorkManager.getInstance(context).cancelUniqueWork(CITATION_DOWNLOADING_WORK)
    }

}