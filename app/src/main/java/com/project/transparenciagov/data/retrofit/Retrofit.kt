package com.project.transparenciagov.data.retrofit

import com.project.transparenciagov.data.api.GovService
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class Retrofit{


    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }

}

