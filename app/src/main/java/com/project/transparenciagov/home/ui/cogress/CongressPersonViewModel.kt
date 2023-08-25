package com.project.transparenciagov.home.ui.cogress

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.viewModelScope
import com.project.transparenciagov.R
import com.project.transparenciagov.core.EmptyListException
import com.project.transparenciagov.core.UseCaseException
import com.project.transparenciagov.home.domain.usecase.CongressPersonUseCase
import com.project.transparenciagov.detail.ui.action.DetailCongressPersonAction
import com.project.transparenciagov.detail.ui.state.DetailCongressPersonState
import com.project.transparenciagov.core.util.BaseViewModel
import com.project.transparenciagov.home.domain.model.StatesModel
import com.project.transparenciagov.home.domain.usecase.AllStatesUseCase
import com.project.transparenciagov.home.ui.cogress.action.CongressPersonAction
import com.project.transparenciagov.home.ui.cogress.state.CongressPersonState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.IOException

internal class CongressPersonViewModel(
    private val useCase: CongressPersonUseCase,
    private val statesUseCase: AllStatesUseCase,
    private val context: Application,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<CongressPersonState, DetailCongressPersonAction>(CongressPersonState()) {

    private var requestPending = false

    init {
        updateState { it.showShimmerLoading(true) }
        getAllCongressPerson()
    }

    fun getAllCongressPerson(incrementPage: Boolean = true) = viewModelScope.launch(dispatcher) {
        if (requestPending) return@launch
        requestPending = true
        val currentPage = stateLiveData.value.getNextPage(incrementPage)
        val states = stateLiveData.value.states.map { it.acronym }
        val query = stateLiveData.value.query
        useCase.invoke(query, currentPage, states)
            .onCompletion { requestPending = false }
            .catch {
                onError(it)
            }
            .collectLatest { list ->
                if (list.isEmpty()) {
                    stateLiveData.value.finishList = true
                } else {
                    updateState { state ->
                        state.setSuccess(list.toMutableList())
                    }

                }
            }
    }


    fun getAllStates() = viewModelScope.launch(dispatcher) {
        statesUseCase.invoke()
            .onStart { updateState { it.copy(progressLoading = true) } }
            .catch { onError(it) }
            .onCompletion { updateState { it.copy(progressLoading = false) } }
            .collectLatest {
                updateState { state -> state.setStateList(it) }
            }
    }

    private fun onError(exception: Throwable) {
        updateState { it.copy(shimmerLoading = false) }
        when (exception) {
            is IOException ->
                CongressPersonAction.ShowError(
                    context.getString(R.string.internet_error),
                    AppCompatResources.getDrawable(context, R.drawable.illus_no_connection)
                ).run()

            is UseCaseException -> {
                CongressPersonAction.ShowToast(exception.messageError).run()

            }

            is EmptyListException -> {

            }

            else -> CongressPersonAction.ShowError(
                context.getString(R.string.message_error_generic),
                AppCompatResources.getDrawable(context, R.drawable.illus_image_generic_error),
            ).run()
        }

    }

    fun onClickSearch(query: String) {
        updateState { it.showShimmerLoading(true) }
        if (stateLiveData.value.query != query) {
            stateLiveData.value.query = query
            stateLiveData.value.page = 0
            stateLiveData.value.finishList = false
            stateLiveData.value.list = mutableListOf()
            getAllCongressPerson()
        }
    }

    fun confirmFilterStates(states: List<StatesModel>) {
        updateState { it.showShimmerLoading(true) }
        stateLiveData.value.states = states
        stateLiveData.value.page = 0
        stateLiveData.value.finishList = false
        stateLiveData.value.list = mutableListOf()
        getAllCongressPerson()

    }


    fun openBottomSheet() {
        CongressPersonAction.OpenBottomSheet.run()
    }

    fun onPagination() {
        if (stateLiveData.value.finishList.not()) {
            getAllCongressPerson()
        }
    }
}