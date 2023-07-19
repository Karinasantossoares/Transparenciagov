package com.project.transparenciagov.home.domain.model



data class CongressPerson(
    val id: Int,
    val name: String,
    val siglaPartido: String,
    val siglaUf: String,
    val urlFoto: String,
    val email: String,
)
