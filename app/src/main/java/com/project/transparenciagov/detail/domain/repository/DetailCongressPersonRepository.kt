package com.project.transparenciagov.detail.domain.repository

import com.project.transparenciagov.core.model.BaseModel
import com.project.transparenciagov.detail.data.model.ExpensesResponse
import com.project.transparenciagov.detail.domain.model.DetailModel
import com.project.transparenciagov.detail.domain.model.ExpenseModel
import com.project.transparenciagov.home.domain.model.CongressPerson
import kotlinx.coroutines.flow.Flow


internal interface DetailCongressPersonRepository {
    fun getDetailCongressPerson(id: Int): Flow<DetailModel>
    fun getExpensesCongressPersons(id: Int): Flow<List<ExpenseModel>>

}