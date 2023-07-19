package com.project.transparenciagov.detail.data.datasource

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.model.DetailPersonResponse

internal interface DetailCongressPersonDataSource {

    suspend fun getDetailCongressPersons(id: Int): BaseModel<DetailPersonResponse>
}