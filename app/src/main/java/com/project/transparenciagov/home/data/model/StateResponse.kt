package com.project.transparenciagov.home.data.model

import com.google.gson.annotations.SerializedName

data class StateResponse(

    @SerializedName("cod")
    val cod: String,

    @SerializedName("sigla")
    val acronym: String,

    @SerializedName("nome")
    val name: String,

    @SerializedName("descricao")
    val description: String
)
