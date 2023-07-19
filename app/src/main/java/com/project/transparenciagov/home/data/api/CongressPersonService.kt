package com.project.transparenciagov.home.data.api

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.home.data.model.DataCongressPersonsResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CongressPersonService {

    @GET("/api/v2/deputados")
    suspend fun getAllCongressPersons(
        @Query("nome") name: String,
        @Query("pagina") page: Int,
        @Query("itens") numberItems: Int?,
        @Query("siglaUf") states: List<String>? = null,
    ): BaseModel<List<DataCongressPersonsResponse>>

}