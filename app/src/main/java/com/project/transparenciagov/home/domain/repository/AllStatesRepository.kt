package com.project.transparenciagov.home.domain.repository

import com.project.transparenciagov.home.domain.model.CongressPerson
import com.project.transparenciagov.home.domain.model.StatesModel
import kotlinx.coroutines.flow.Flow


internal interface AllStatesRepository {
    fun getAllStates(): Flow<List<StatesModel>>
}