package com.project.transparenciagov.categoryfront.data.datasource

import com.project.transparenciagov.categoryfront.data.model.FrontCongressPersonResponse
import com.project.transparenciagov.categoryfront.data.model.ResultFrontCongressPersonResponse
import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.model.DetailPersonResponse
import com.project.transparenciagov.detail.data.model.ExpensesResponse

internal interface FrontCongressPersonDataSource {

    suspend fun getFrontCongressPersons(id: Int): BaseModel<List<FrontCongressPersonResponse>>
    suspend fun getResultFrontCongressPersons(id: Int): BaseModel<ResultFrontCongressPersonResponse>
}