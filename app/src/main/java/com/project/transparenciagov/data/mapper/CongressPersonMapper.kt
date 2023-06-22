package com.project.transparenciagov.data.mapper

import com.project.transparenciagov.data.model.CongressPersonResponse
import com.project.transparenciagov.domain.model.CongressPerson
import com.project.transparenciagov.domain.model.DataCongressPersons

class CongressPersonMapper {
    fun parseToEntity(congressPersonResponse: CongressPersonResponse): CongressPerson =
        CongressPerson(
            data = congressPersonResponse.data.map {
                DataCongressPersons(
                    id = it.id,
                    name = it.name,
                    siglaPartido = it.siglaPartido,
                    siglaUf = it.siglaUf,
                    urlFoto = it.urlFoto,
                    email = it.email
                )
            }
        )
}