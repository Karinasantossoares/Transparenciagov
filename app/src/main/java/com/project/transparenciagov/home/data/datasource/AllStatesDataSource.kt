package com.project.transparenciagov.home.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.home.data.model.StateResponse

internal interface AllStatesDataSource {
    suspend fun getAllStates(): BaseModel<List<StateResponse>>
}