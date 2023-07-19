package com.project.transparenciagov.home.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.home.data.api.CongressPersonService
import com.project.transparenciagov.home.data.model.DataCongressPersonsResponse
import kotlinx.coroutines.flow.Flow

internal class AllCongressPersonDataSourceImpl(private val service: CongressPersonService) :
    AllCongressPersonDataSource {

    override suspend fun getAllCongressPersons(
        name: String,
        page: Int,
        numberItems: Int?,
        states: List<String>?,
    ): BaseModel<List<DataCongressPersonsResponse>> =
        service.getAllCongressPersons(name, page, numberItems, states)
}