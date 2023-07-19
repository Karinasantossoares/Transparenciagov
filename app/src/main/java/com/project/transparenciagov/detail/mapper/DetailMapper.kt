package com.project.transparenciagov.detail.mapper

import com.project.transparenciagov.detail.data.model.DetailPersonResponse
import com.project.transparenciagov.detail.domain.model.DetailModel
import com.project.transparenciagov.detail.domain.model.Gabinete
import com.project.transparenciagov.detail.domain.model.LastStatus
import com.project.transparenciagov.home.data.model.DataCongressPersonsResponse
import com.project.transparenciagov.home.domain.model.CongressPerson

class DetailMapper {
    fun parseToEntity(item: DetailPersonResponse): DetailModel =
        DetailModel(
            id = item.id,
            civilName = item.civilName ?: "",
            lastStatus = LastStatus(
                urlFoto = item.lastStatus.urlFoto,
                siglaPartido = item.lastStatus.siglaPartido,
                email = item.lastStatus.email,
                nomeEleitoral = item.lastStatus.nomeEleitoral,
                situacao = item.lastStatus.situacao,
                condicaoEleitoral = item.lastStatus.condicaoEleitoral,
                data = item.lastStatus.data,
                gabinete = Gabinete(
                    telefone = item.lastStatus.gabinete.telefone,
                    nome = item.lastStatus.gabinete.nome,
                    predio = item.lastStatus.gabinete.predio,
                    sala = item.lastStatus.gabinete.sala,
                    andar = item.lastStatus.gabinete.andar
                )
            )

        )
}
