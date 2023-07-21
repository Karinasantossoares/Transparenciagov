package com.project.transparenciagov.detail.ui

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.viewModelScope
import com.project.transparenciagov.R
import com.project.transparenciagov.core.EmptyListException
import com.project.transparenciagov.core.UseCaseException
import com.project.transparenciagov.core.util.BaseViewModel
import com.project.transparenciagov.detail.domain.usecase.GetDetailCongressPersonUseCase
import com.project.transparenciagov.detail.domain.usecase.GetExpenseUseCase
import com.project.transparenciagov.detail.ui.action.DetailCongressPersonAction
import com.project.transparenciagov.detail.ui.state.DetailCongressPersonState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.io.IOException

internal class DetailCongressPersonViewModel(
    private val useCase: GetDetailCongressPersonUseCase,
    private val expenseUseCase: GetExpenseUseCase,
    private val context: Application,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val id: Int
) : BaseViewModel<DetailCongressPersonState, DetailCongressPersonAction>(DetailCongressPersonState()) {

    init {
        getDetailCongressPerson()
        getExpenseCongressPerson()
    }

    private fun getDetailCongressPerson() = viewModelScope.launch(dispatcher) {
        useCase.invoke(id)
            .onCompletion { updateState { it.showLoading(false) } }
            .catch {
                onError(it)
            }
            .collectLatest { detail ->
                updateState { it.setSuccess(detail) }
            }
    }

    private fun getExpenseCongressPerson() = viewModelScope.launch(dispatcher) {
        expenseUseCase.invoke(id)
            .onCompletion { updateState { it.showLoading(false) } }
            .catch {
                onError(it)
            }
            .collectLatest { list ->

            }
    }


    private fun onError(exception: Throwable) {
        updateState { it.showLoading(false) }
        when (exception) {
            is IOException ->
                DetailCongressPersonAction.ShowError(
                    context.getString(R.string.internet_error),
                    AppCompatResources.getDrawable(context, R.drawable.illus_no_connection)
                ).run()

            is UseCaseException -> {
                DetailCongressPersonAction.ShowToast(exception.messageError).run()

            }

            is EmptyListException -> {

            }

            else -> DetailCongressPersonAction.ShowError(
                context.getString(R.string.message_error_generic),
                AppCompatResources.getDrawable(context, R.drawable.illus_image_generic_error),
            ).run()
        }

    }

}