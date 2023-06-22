package com.project.transparenciagov.data.model

import com.google.gson.annotations.SerializedName

data class CongressPersonResponse(
    @SerializedName("dados") val data: List<DataCongressPersonsResponse>
)

data class DataCongressPersonsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nome") val name: String,
    @SerializedName("siglaPartido") val siglaPartido: String,
    @SerializedName("siglaUf") val siglaUf: String,
    @SerializedName("urlFoto") val urlFoto: String,
    @SerializedName("email") val email: String,
)
