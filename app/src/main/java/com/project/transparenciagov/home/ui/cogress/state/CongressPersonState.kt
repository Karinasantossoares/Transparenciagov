package com.project.transparenciagov.home.ui.cogress.state

import com.project.transparenciagov.home.domain.model.CongressPerson
import com.project.transparenciagov.home.domain.model.StatesModel

data class CongressPersonState(
    var list: MutableList<CongressPerson> = mutableListOf(),
    val shimmerLoading: Boolean = false,
    val error: Boolean = false,
    var finishList: Boolean = false,
    val progressLoading: Boolean = false,
    val listStatesModel: List<StatesModel> = emptyList(),
    var query: String = "",
    var page: Int = 0,
    var states: List<StatesModel> = emptyList()
) {

    fun showShimmerLoading(isLoading: Boolean) = copy(shimmerLoading = isLoading)

    fun setSuccess(currentList: MutableList<CongressPerson>) = copy(
        shimmerLoading = false,
        list = mutableListOf(*list.toTypedArray()).apply {
            addAll(currentList)
        },
    )

    fun setStateList(listStates: List<StatesModel>) = copy(
        listStatesModel = listStates
    )

    fun hasFilters(): Boolean {
        return states.isNotEmpty() || query.isNotEmpty()
    }

    fun getNextPage(incrementPage: Boolean): Int {
        return if (incrementPage) ++page else page
    }
}