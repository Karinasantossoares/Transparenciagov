package com.project.transparenciagov.detail.data.api

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.model.DetailPersonResponse
import com.project.transparenciagov.detail.data.model.ExpensesResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DetailService {

    @GET("/api/v2/deputados/{id}")
    suspend fun getDetailCongressPersons(@Path("id") id: Int): BaseModel<DetailPersonResponse>

    @GET("/api/v2/deputados/{id}/despesas")
    suspend fun getExpensesCongressPersons(@Path("id") id: Int): BaseModel<List<ExpensesResponse>>


}