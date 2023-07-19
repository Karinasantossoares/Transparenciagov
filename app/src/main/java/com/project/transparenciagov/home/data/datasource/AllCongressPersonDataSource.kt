package com.project.transparenciagov.home.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.home.data.model.DataCongressPersonsResponse
import kotlinx.coroutines.flow.Flow

internal interface AllCongressPersonDataSource {

    suspend fun getAllCongressPersons(
        name: String,
        page: Int,
        numberItems: Int?,
        states: List<String>? = null,
    ): BaseModel<List<DataCongressPersonsResponse>>
}