package com.project.transparenciagov.data.repository

import com.project.transparenciagov.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.data.mapper.CongressPersonMapper
import com.project.transparenciagov.domain.model.CongressPerson
import com.project.transparenciagov.domain.repository.AllCongressPersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException


internal class AllCongressPersonRepositoryImpl(private val dataSource: AllCongressPersonDataSource) :
    AllCongressPersonRepository {
    override suspend fun getAllCongressPersons(): Flow<CongressPerson> {
        return flow {
            emit(dataSource.getAllCongressPersons())
        }.map {
            CongressPersonMapper().parseToEntity(it)
        }
    }

}