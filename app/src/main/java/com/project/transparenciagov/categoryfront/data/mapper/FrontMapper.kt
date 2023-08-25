package com.project.transparenciagov.categoryfront.data.mapper

import com.project.transparenciagov.categoryfront.data.model.FrontCongressPersonResponse
import com.project.transparenciagov.categoryfront.domain.model.FrontCongressPersonModel

class FrontMapper {

    fun parseToEntity(item: FrontCongressPersonResponse): FrontCongressPersonModel =
        FrontCongressPersonModel(
            id = item.id,
            title = item.title
        )
}