package com.project.transparenciagov

import android.app.Application
import com.project.transparenciagov.categoryfront.di.detailModule
import com.project.transparenciagov.core.di.baseModule
import com.project.transparenciagov.detail.di.frontModule
import com.project.transparenciagov.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(baseModule, homeModule, detailModule, frontModule)
        }
    }
}
