package com.project.transparenciagov.domain.repository

import com.project.transparenciagov.domain.model.CongressPerson
import kotlinx.coroutines.flow.Flow


internal interface AllCongressPersonRepository {

    suspend fun getAllCongressPersons(): Flow<CongressPerson>
}