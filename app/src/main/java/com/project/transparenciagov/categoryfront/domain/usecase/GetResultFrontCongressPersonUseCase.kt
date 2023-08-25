package com.project.transparenciagov.categoryfront.domain.usecase

import com.project.transparenciagov.categoryfront.domain.repository.FrontCongressPersonRepository

internal class GetResultFrontCongressPersonUseCase(val repository: FrontCongressPersonRepository) {

    fun invoke(id: Int) = repository.getResultFrontCongressPersons(id)
}