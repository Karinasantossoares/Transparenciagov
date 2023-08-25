package com.project.transparenciagov.categoryfront.data.api

import com.project.transparenciagov.categoryfront.data.model.FrontCongressPersonResponse
import com.project.transparenciagov.categoryfront.data.model.ResultFrontCongressPersonResponse
import com.project.transparenciagov.core.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface FrontCongressPersonService {

    @GET("/api/v2/deputados/{id}/frentes")
    suspend fun getFrontCongressPersons(@Path("id") id: Int): BaseModel<List<FrontCongressPersonResponse>>


    @GET("/api/v2/frentes/{id}")
    suspend fun getResultFrontCongressPersons(@Path("id") id: Int): BaseModel<ResultFrontCongressPersonResponse>
}