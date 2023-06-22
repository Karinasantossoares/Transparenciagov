package com.project.transparenciagov.data.datasource

import com.project.transparenciagov.data.model.CongressPersonResponse
import kotlinx.coroutines.flow.Flow

internal interface AllCongressPersonDataSource {

    suspend fun getAllCongressPersons(): CongressPersonResponse
}