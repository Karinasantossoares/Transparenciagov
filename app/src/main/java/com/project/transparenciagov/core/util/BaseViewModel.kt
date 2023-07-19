package com.project.transparenciagov.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel<State, Event>(
    private val state: State
) : ViewModel() {

    val stateLiveData = MutableStateFlow(state)
    val eventLiveData = MutableStateFlow(BaseEvent())


    protected fun updateState(newState: (State) -> State) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveData.update(newState)
        }
    }

    protected fun BaseEvent.run() {
        viewModelScope.launch(Dispatchers.Main) {
            eventLiveData.update { this@run }
            eventLiveData.update { BaseEvent() }
        }
    }
}