package kz.moviedb.presentation.utils.work_manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kz.moviedb.domain.model.Either
import kz.moviedb.domain.usecase.CitationUseCase
import org.koin.java.KoinJavaComponent.inject

class CitationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val citationUseCase: CitationUseCase by inject(CitationUseCase::class.java)

    override suspend fun doWork(): Result {
        return when (citationUseCase.getCitationFromInternet()) {
            is Either.Success -> {
                Result.success()
            }
            is Either.Error -> {
                Result.failure()
            }
        }
    }
}