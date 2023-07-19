package com.project.transparenciagov.home.data.repository

import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.home.data.mapper.CongressPersonMapper
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


internal class AllCongressPersonRepositoryImpl(private val dataSource: AllCongressPersonDataSource) :
    AllCongressPersonRepository {
    override fun getAllCongressPerson(
        name: String,
        page: Int,
        numberItems: Int?,
        states: List<String>?
    ) = flow {
        emit(dataSource.getAllCongressPersons(name, page, numberItems, states))
    }.map { response ->
        response.dados.map {
            CongressPersonMapper().parseToEntity(it)
        }
    }
}