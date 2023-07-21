package com.project.transparenciagov.detail.di

import com.project.transparenciagov.detail.data.api.DetailService
import com.project.transparenciagov.detail.data.datasource.DetailCongressPersonDataSource
import com.project.transparenciagov.detail.data.datasource.DetailCongressPersonDataSourceImpl
import com.project.transparenciagov.detail.data.repository.DetailCongressPersonRepositoryImpl
import com.project.transparenciagov.detail.domain.repository.DetailCongressPersonRepository
import com.project.transparenciagov.detail.domain.usecase.GetDetailCongressPersonUseCase
import com.project.transparenciagov.detail.domain.usecase.GetExpenseUseCase
import com.project.transparenciagov.detail.ui.DetailCongressPersonViewModel
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

val detailModule = module {
    factory { get<Retrofit>().create(DetailService::class.java) }
    factory<DetailCongressPersonRepository> {
        DetailCongressPersonRepositoryImpl(dataSource = get())
    }
    factory<DetailCongressPersonDataSource> {
        DetailCongressPersonDataSourceImpl(service = get())
    }
    factory { GetDetailCongressPersonUseCase(repository = get()) }
    factory { GetExpenseUseCase(repository = get()) }
    viewModel { parameters->
        DetailCongressPersonViewModel(
            useCase = get(),
            expenseUseCase = get(),
            context = get(),
            id = parameters.get()
        )
    }

}