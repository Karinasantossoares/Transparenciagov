package com.project.transparenciagov.data.datasource

import com.project.transparenciagov.data.api.GovService
import com.project.transparenciagov.data.model.CongressPersonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

internal class AllCongressPersonDataSourceImpl(private val service: GovService) :
    AllCongressPersonDataSource {

    override suspend fun getAllCongressPersons(): CongressPersonResponse =
        service.getAllCongressPersons()
}