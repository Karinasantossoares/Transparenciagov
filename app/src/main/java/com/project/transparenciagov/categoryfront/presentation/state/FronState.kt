package com.project.transparenciagov.categoryfront.presentation.state

import com.project.transparenciagov.categoryfront.domain.model.FrontCongressPersonModel

data class FrontState(
    var list: List<FrontCongressPersonModel> = emptyList(),
    val loading: Boolean = false
) {

    fun setSuccess(frontList: List<FrontCongressPersonModel>) = copy(
        list = frontList
    )

    fun showLoading(isLoading: Boolean) = copy(loading = isLoading)


}