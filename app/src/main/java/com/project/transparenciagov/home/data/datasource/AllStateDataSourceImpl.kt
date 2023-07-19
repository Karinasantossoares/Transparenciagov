package com.project.transparenciagov.home.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.home.data.api.ReferenceService
import com.project.transparenciagov.home.data.model.StateResponse

internal class AllStateDataSourceImpl(private val service: ReferenceService) : AllStatesDataSource {

    override suspend fun getAllStates(): BaseModel<List<StateResponse>> {
      return service.getAllStates()
    }
}