package com.project.transparenciagov.home.data.mapper

import com.project.transparenciagov.home.data.model.StateResponse
import com.project.transparenciagov.home.domain.model.CongressPerson
import com.project.transparenciagov.home.domain.model.StatesModel

class StatesMapper {

    fun parseToEntity(item: StateResponse): StatesModel =
        StatesModel(
            cod = item.cod,
            acronym = item.acronym,
            name = item.name,
            description = item.description
        )
}