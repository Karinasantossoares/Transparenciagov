package com.project.transparenciagov.core.di

import com.project.transparenciagov.home.data.retrofit.RetrofitBuilder
import org.koin.dsl.module

val baseModule = module {
    factory { RetrofitBuilder().getInstance() }

}