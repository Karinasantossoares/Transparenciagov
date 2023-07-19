package com.project.transparenciagov.home.domain.repository

import com.project.transparenciagov.home.domain.model.CongressPerson
import kotlinx.coroutines.flow.Flow


internal interface AllCongressPersonRepository {
    fun getAllCongressPerson(
        name: String,
        page: Int,
        numberItems: Int?,
        states: List<String>?
    ): Flow<List<CongressPerson>>
}