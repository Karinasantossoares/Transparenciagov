package com.project.transparenciagov.categoryfront.data.repository

import com.project.transparenciagov.categoryfront.data.datasource.FrontCongressPersonDataSource
import com.project.transparenciagov.categoryfront.data.mapper.FrontMapper
import com.project.transparenciagov.categoryfront.data.mapper.ResultFrontMapper
import com.project.transparenciagov.categoryfront.domain.repository.FrontCongressPersonRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


internal class FrontCongressPersonRepositoryImpl(private val dataSource: FrontCongressPersonDataSource) :
    FrontCongressPersonRepository {


    override fun getFrontCongressPersons(id: Int) = flow {
        emit(dataSource.getFrontCongressPersons(id))
    }.map {
        it.dados.map { front ->
            FrontMapper().parseToEntity(front)
        }
    }

    override fun getResultFrontCongressPersons(id: Int) = flow {
        emit(dataSource.getResultFrontCongressPersons(id))
    }.map {
        ResultFrontMapper().parseToEntity(it.dados)
    }
}
