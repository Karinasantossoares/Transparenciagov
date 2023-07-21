package com.project.transparenciagov.detail.data.model

import com.google.gson.annotations.SerializedName

data class DetailPersonResponse(
    @SerializedName("id") val id: String,
    @SerializedName("nomeCivil") val civilName: String,
    @SerializedName("ultimoStatus") val lastStatus: LastStatus,
    @SerializedName("dataNascimento") val dateOfBirthiday: String
)

data class LastStatus(
    @SerializedName("urlFoto") val urlFoto: String,
    @SerializedName("siglaPartido") val siglaPartido: String,
    @SerializedName("email") val email: String,
    @SerializedName("nomeEleitoral") val nomeEleitoral: String,
    @SerializedName("gabinete") val gabinete: Gabinete,
    @SerializedName("situacao") val situacao: String,
    @SerializedName("condicaoEleitoral") val condicaoEleitoral: String,
    @SerializedName("data") val data: String,

    )

data class Gabinete(
    @SerializedName("telefone") val telefone: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("predio") val predio: String,
    @SerializedName("sala") val sala: String,
    @SerializedName("andar") val andar: String,
)