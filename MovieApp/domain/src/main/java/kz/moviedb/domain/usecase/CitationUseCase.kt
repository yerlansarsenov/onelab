package kz.moviedb.domain.usecase

import kz.moviedb.domain.repository.CitationRepository

/**
 * Created by Sarsenov Yerlan on 27.01.2021.
 */
class CitationUseCase(private val repository: CitationRepository) {
    suspend fun getCitationFromInternet() = repository.getCitationFromInternet()

    suspend fun getAllCitationFromDatabase() = repository.getAllCitationFromDatabase()

    fun getAllCitationFromDatabaseFlow() = repository.getAllCitationFromDatabaseFlow()
}