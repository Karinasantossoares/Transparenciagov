package com.project.transparenciagov.home.data.mapper

import com.project.transparenciagov.home.data.model.DataCongressPersonsResponse
import com.project.transparenciagov.home.domain.model.CongressPerson

class CongressPersonMapper {
    fun parseToEntity(item: DataCongressPersonsResponse): CongressPerson =
        CongressPerson(
            id = item.id?:0,
            name = item.name.orEmpty(),
            siglaPartido = item.siglaPartido.orEmpty(),
            siglaUf = item.siglaUf.orEmpty(),
            urlFoto = item.urlFoto.orEmpty(),
            email = item.email.orEmpty()
        )
}
