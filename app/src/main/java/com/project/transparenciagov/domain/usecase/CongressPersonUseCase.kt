package com.project.transparenciagov.domain.usecase

import com.project.transparenciagov.domain.repository.AllCongressPersonRepository

internal class CongressPersonUseCase(private val repository: AllCongressPersonRepository) {
    suspend fun invoke() = repository.getAllCongressPersons()
}