package com.project.transparenciagov.categoryfront.data.datasource

import com.project.transparenciagov.categoryfront.data.api.FrontCongressPersonService
import com.project.transparenciagov.categoryfront.data.model.FrontCongressPersonResponse
import com.project.transparenciagov.categoryfront.data.model.ResultFrontCongressPersonResponse
import com.project.transparenciagov.core.model.BaseModel

internal class FrontCongressPersonDataSourceImpl(private val service: FrontCongressPersonService) :
    FrontCongressPersonDataSource {
    override suspend fun getFrontCongressPersons(id: Int): BaseModel<List<FrontCongressPersonResponse>> =
        service.getFrontCongressPersons(id)

    override suspend fun getResultFrontCongressPersons(id: Int): BaseModel<ResultFrontCongressPersonResponse> =
        service.getResultFrontCongressPersons(id)

}