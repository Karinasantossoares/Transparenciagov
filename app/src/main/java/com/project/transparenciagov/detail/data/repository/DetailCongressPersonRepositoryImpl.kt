package com.project.transparenciagov.detail.data.repository

import com.project.transparenciagov.detail.data.datasource.DetailCongressPersonDataSource
import com.project.transparenciagov.detail.data.model.ExpensesResponse
import com.project.transparenciagov.detail.domain.model.ExpenseModel
import com.project.transparenciagov.detail.domain.repository.DetailCongressPersonRepository
import com.project.transparenciagov.detail.mapper.DetailMapper
import com.project.transparenciagov.detail.mapper.ExpenseMapper
import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.home.data.mapper.CongressPersonMapper
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


internal class DetailCongressPersonRepositoryImpl(private val dataSource: DetailCongressPersonDataSource) :
    DetailCongressPersonRepository {

    override fun getDetailCongressPerson(id: Int) = flow {
        emit(dataSource.getDetailCongressPersons(id))
    }.map {
        DetailMapper().parseToEntity(it.dados)
    }

    override fun getExpensesCongressPersons(id: Int): Flow<List<ExpenseModel>> = flow {
        emit(dataSource.getExpensesCongressPersons(id))
    }.map {
        it.dados.map { response ->
            ExpenseMapper().parseToEntity(response)
        }
    }
}
