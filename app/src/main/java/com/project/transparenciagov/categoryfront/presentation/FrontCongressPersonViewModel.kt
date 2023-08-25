package com.project.transparenciagov.categoryfront.presentation

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.viewModelScope
import com.project.transparenciagov.R
import com.project.transparenciagov.categoryfront.domain.usecase.FrontCongressPersonUseCase
import com.project.transparenciagov.categoryfront.domain.usecase.GetResultFrontCongressPersonUseCase
import com.project.transparenciagov.categoryfront.presentation.action.FrontAction
import com.project.transparenciagov.categoryfront.presentation.state.FrontState
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

internal class FrontCongressPersonViewModel(
    private val useCase: FrontCongressPersonUseCase,
    private val resultFrontUseCase: GetResultFrontCongressPersonUseCase,
    private val context: Application,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val id: Int
) : BaseViewModel<FrontState, FrontAction>(FrontState()) {

    init {
        getFrontCongressPerson()
    }

    private fun getFrontCongressPerson() = viewModelScope.launch(dispatcher) {
        useCase.invoke(id)
            .onCompletion { updateState { it.showLoading(false) } }
            .catch {
                onError(it)
            }
            .collectLatest { front ->

                updateState {
                    it.setSuccess(front)
                }
            }
    }


    private fun onError(exception: Throwable) {
        updateState { it.showLoading(false) }
        when (exception) {
            is IOException ->
                FrontAction.ShowError(
                    context.getString(R.string.internet_error),
                    AppCompatResources.getDrawable(context, R.drawable.illus_no_connection)
                ).run()

            is UseCaseException -> {

            }

            is EmptyListException -> {

            }

            else -> FrontAction.ShowError(
                context.getString(R.string.message_error_generic),
                AppCompatResources.getDrawable(context, R.drawable.illus_image_generic_error),
            ).run()
        }

    }

    fun onKnowMore(idDocument: Int) = viewModelScope.launch {
        FrontAction.OpenBottomSheet.run()
        resultFrontUseCase.invoke(idDocument)
            .onCompletion { updateState { it.showLoading(false) } }
            .catch {
                onError(it)
            }
            .collectLatest { front ->
                FrontAction.UpdateDataBottomSheet(front).run()
            }
    }

}