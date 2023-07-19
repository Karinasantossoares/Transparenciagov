package com.project.transparenciagov.home.di

import com.project.transparenciagov.home.data.api.CongressPersonService
import com.project.transparenciagov.home.data.api.ReferenceService
import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSource
import com.project.transparenciagov.home.data.datasource.AllCongressPersonDataSourceImpl
import com.project.transparenciagov.home.data.datasource.AllStateDataSourceImpl
import com.project.transparenciagov.home.data.datasource.AllStatesDataSource
import com.project.transparenciagov.home.data.repository.AllCongressPersonRepositoryImpl
import com.project.transparenciagov.home.data.repository.AllStatesRepositoryImpl
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import com.project.transparenciagov.home.domain.repository.AllStatesRepository
import com.project.transparenciagov.home.domain.usecase.AllStatesUseCase
import com.project.transparenciagov.home.domain.usecase.CongressPersonUseCase
import com.project.transparenciagov.home.ui.cogress.CongressPersonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    factory { get<Retrofit>().create(CongressPersonService::class.java) }
    factory { get<Retrofit>().create(ReferenceService::class.java) }
    factory<AllCongressPersonRepository> {
        AllCongressPersonRepositoryImpl(dataSource = get())
    }
    factory<AllStatesRepository> {
        AllStatesRepositoryImpl(dataSource = get())
    }
    factory<AllCongressPersonDataSource> {
        AllCongressPersonDataSourceImpl(
            service = get()
        )
    }
    factory<AllStatesDataSource> {
        AllStateDataSourceImpl(
            service = get()
        )
    }
    factory { CongressPersonUseCase(repository = get(), get()) }
    factory { AllStatesUseCase(repository = get()) }
    viewModel { CongressPersonViewModel(get(), get(), get()) }
}