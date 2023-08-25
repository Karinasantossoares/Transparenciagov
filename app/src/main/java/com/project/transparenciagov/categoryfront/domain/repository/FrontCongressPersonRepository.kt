package com.project.transparenciagov.categoryfront.domain.repository

import com.project.transparenciagov.categoryfront.domain.model.FrontCongressPersonModel
import com.project.transparenciagov.categoryfront.domain.model.ResultFrontCongressPersonModel
import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.model.ExpensesResponse
import com.project.transparenciagov.detail.domain.model.DetailModel
import com.project.transparenciagov.detail.domain.model.ExpenseModel
import com.project.transparenciagov.home.domain.model.CongressPerson
import kotlinx.coroutines.flow.Flow


internal interface FrontCongressPersonRepository {
    fun getFrontCongressPersons(id: Int): Flow<List<FrontCongressPersonModel>>
    fun getResultFrontCongressPersons(id: Int): Flow<ResultFrontCongressPersonModel>
}