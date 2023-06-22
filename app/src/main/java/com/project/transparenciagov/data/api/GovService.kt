package com.project.transparenciagov.data.api

import com.project.transparenciagov.data.model.CongressPersonResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

internal interface GovService {

    @GET
    suspend fun getAllCongressPersons(): CongressPersonResponse
}