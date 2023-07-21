package com.project.transparenciagov.detail.domain.model

import com.google.gson.annotations.SerializedName

data class DetailModel(
    val id: String = "",
    val civilName: String = "",
    val dateOfBirthday : String = "",
    val lastStatus: LastStatus = LastStatus()
)

data class LastStatus(
    val urlFoto: String = "",
    val siglaPartido: String = "",
    val email: String = "",
    val nomeEleitoral: String = "",
    val gabinete: Gabinete = Gabinete(),
    val situacao: String = "",
    val condicaoEleitoral: String = "",
    val data: String = ""

)

data class Gabinete(
    val telefone: String = "",
    val nome: String = "",
    val predio: String = "",
    val sala: String = "",
    val andar: String = "",
)