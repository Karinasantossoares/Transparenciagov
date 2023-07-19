package com.project.transparenciagov.home.domain.usecase

import android.app.Application
import com.project.transparenciagov.R
import com.project.transparenciagov.core.EmptyListException
import com.project.transparenciagov.core.UseCaseException
import com.project.transparenciagov.home.domain.model.CongressPerson
import com.project.transparenciagov.home.domain.repository.AllCongressPersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

internal class CongressPersonUseCase(
    private val repository: AllCongressPersonRepository,
    private val context: Application
) {
    fun invoke(
        name: String,
        page: Int,
        states: List<String>?,
    ): Flow<List<CongressPerson>> {
        return repository.getAllCongressPerson(name, page, NUMBER_ITEMS, states).onStart {
            if (name.isNotEmpty() && name.length < 3) {
                throw UseCaseException(context.getString(R.string.text_empty_search_view))
            }
        }.onEmpty {
            throw EmptyListException
        }
    }

    companion object {
        private const val NUMBER_ITEMS = 21
    }
}