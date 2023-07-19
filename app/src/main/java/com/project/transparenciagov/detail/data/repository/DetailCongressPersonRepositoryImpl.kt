package com.project.transparenciagov.detail.data.repository

import com.project.transparenciagov.detail.data.datasource.DetailCongressPersonDataSource
import com.project.transparenciagov.detail.domain.repository.DetailCongressPersonRepository
import com.project.transparenciagov.detail.mapper.DetailMapper
import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.home.data.mapper.CongressPersonMapper
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


internal class DetailCongressPersonRepositoryImpl(private val dataSource: DetailCongressPersonDataSource) :
    DetailCongressPersonRepository {

    override fun getDetailCongressPerson(id: Int) = flow {
        emit(dataSource.getDetailCongressPersons(id))
    }.map {
        DetailMapper().parseToEntity(it.dados)
    }
}
