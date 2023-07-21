package com.project.transparenciagov.detail.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.model.DetailPersonResponse
import com.project.transparenciagov.detail.data.model.ExpensesResponse

internal interface DetailCongressPersonDataSource {

    suspend fun getDetailCongressPersons(id: Int): BaseModel<DetailPersonResponse>
    suspend fun getExpensesCongressPersons(id: Int): BaseModel<List<ExpensesResponse>>
}