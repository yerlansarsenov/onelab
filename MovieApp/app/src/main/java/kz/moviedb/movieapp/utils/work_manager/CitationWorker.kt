package kz.moviedb.movieapp.utils.work_manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kz.moviedb.movieapp.repository.CitationRepository
import org.koin.java.KoinJavaComponent.inject

/**
 * Created by Sarsenov Yerlan on 08.01.2021.
 */

const val METHOD = "getQuote"
const val FORMAT = "json"

class CitationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val repository: CitationRepository by inject(CitationRepository::class.java)

    override suspend fun doWork(): Result = repository.getCitationFromInternet()
}