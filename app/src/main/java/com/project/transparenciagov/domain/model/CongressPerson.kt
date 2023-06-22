package com.project.transparenciagov.domain.model

data class CongressPerson(
    val data: List<DataCongressPersons>
)

data class DataCongressPersons(
    val id: Int,
    val name: String,
    val siglaPartido: String,
    val siglaUf: String,
    val urlFoto: String,
    val email: String,
)
