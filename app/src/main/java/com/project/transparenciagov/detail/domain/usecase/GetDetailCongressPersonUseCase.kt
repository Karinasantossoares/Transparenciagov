package com.project.transparenciagov.detail.domain.usecase

import com.project.transparenciagov.detail.domain.repository.DetailCongressPersonRepository

internal class GetDetailCongressPersonUseCase(val repository: DetailCongressPersonRepository) {

    fun invoke(id: Int) = repository.getDetailCongressPerson(id)
}