package com.project.transparenciagov.home.domain.usecase

import android.app.Application
import com.project.transparenciagov.R
import com.project.transparenciagov.core.EmptyListException
import com.project.transparenciagov.core.UseCaseException
import com.project.transparenciagov.home.domain.model.CongressPerson
import com.project.transparenciagov.home.domain.model.StatesModel
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import com.project.transparenciagov.home.domain.repository.AllStatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

internal class AllStatesUseCase(private val repository: AllStatesRepository) {
    fun invoke(): Flow<List<StatesModel>> {
        return repository.getAllStates()
    }


}