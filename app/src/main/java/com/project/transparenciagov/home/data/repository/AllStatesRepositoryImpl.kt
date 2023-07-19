package com.project.transparenciagov.home.data.repository

import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.home.data.datasource.AllStatesDataSource
import com.project.transparenciagov.home.data.mapper.CongressPersonMapper
import com.project.transparenciagov.home.data.mapper.StatesMapper
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import com.project.transparenciagov.home.domain.repository.AllStatesRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


internal class AllStatesRepositoryImpl(private val dataSource: AllStatesDataSource) :
    AllStatesRepository {
    override fun getAllStates() = flow {
        emit(dataSource.getAllStates())
    }.map { response ->
        response.dados.map {
            StatesMapper().parseToEntity(it)
        }
    }
}