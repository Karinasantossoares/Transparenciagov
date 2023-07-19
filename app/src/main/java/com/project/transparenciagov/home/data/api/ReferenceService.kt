package com.project.transparenciagov.home.data.api

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.home.data.model.StateResponse
import retrofit2.http.GET

internal interface ReferenceService {

    @GET("/api/v2/referencias/deputados/siglaUF")
    suspend fun getAllStates(): BaseModel<List<StateResponse>>
}