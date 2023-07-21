package com.project.transparenciagov.detail.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.api.DetailService
import com.project.transparenciagov.detail.data.model.DetailPersonResponse
import com.project.transparenciagov.detail.data.model.ExpensesResponse
import com.project.transparenciagov.home.data.api.CongressPersonService
import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.home.data.model.DataCongressPersonsResponse

internal class DetailCongressPersonDataSourceImpl(private val service: DetailService) :
    DetailCongressPersonDataSource {

    override suspend fun getDetailCongressPersons(id: Int): BaseModel<DetailPersonResponse> =
        service.getDetailCongressPersons(id)

    override suspend fun getExpensesCongressPersons(id: Int): BaseModel<List<ExpensesResponse>> =
        service.getExpensesCongressPersons(id)

}