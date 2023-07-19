package com.project.transparenciagov.home.data.model

import com.google.gson.annotations.SerializedName

data class DataCongressPersonsResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("nome") val name: String?,
    @SerializedName("siglaPartido") val siglaPartido: String?,
    @SerializedName("siglaUf") val siglaUf: String?,
    @SerializedName("urlFoto") val urlFoto: String?,
    @SerializedName("email") val email: String?,
)
