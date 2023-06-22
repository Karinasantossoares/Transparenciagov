package com.project.transparenciagov.di

import com.project.transparenciagov.data.api.GovService
import com.project.transparenciagov.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.data.datasource.AllCongressPersonDataSourceImpl
import com.project.transparenciagov.data.repository.AllCongressPersonRepositoryImpl
import com.project.transparenciagov.domain.repository.AllCongressPersonRepository
import com.project.transparenciagov.domain.usecase.CongressPersonUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val govModule = module {
    factory { get<Retrofit>().create(GovService::class.java) }
    factory<AllCongressPersonRepository> {
        AllCongressPersonRepositoryImpl(dataSource = get())
    }
    factory<AllCongressPersonDataSource> {
        AllCongressPersonDataSourceImpl(
            service = get()
        )
    }
    factory { CongressPersonUseCase(repository = get()) }
}