package com.project.transparenciagov.categoryfront.data.mapper

import com.project.transparenciagov.categoryfront.data.model.ResultFrontCongressPersonResponse
import com.project.transparenciagov.categoryfront.domain.model.ItemSituation
import com.project.transparenciagov.categoryfront.domain.model.ResultFrontCongressPersonModel

class ResultFrontMapper {

    fun parseToEntity(item: ResultFrontCongressPersonResponse): ResultFrontCongressPersonModel =
        ResultFrontCongressPersonModel(
            situation = item.situation.split("\r\n").map { item ->
                val title = item.split("-")[1]
                val date = item.split("-")[0]
                ItemSituation(title, date)
            },
            urlDocument = item.urlDocument,
            title = item.title
        )
}