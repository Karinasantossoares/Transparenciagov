package com.project.transparenciagov.detail.ui.state

import com.project.transparenciagov.detail.domain.model.DetailModel
import com.project.transparenciagov.detail.domain.model.ExpenseModel
import com.project.transparenciagov.detail.domain.model.LastStatus
import com.project.transparenciagov.home.domain.model.CongressPerson
import com.project.transparenciagov.home.domain.model.StatesModel

data class DetailCongressPersonState(
    val detailModel: DetailModel = DetailModel(),
    val isLoading: Boolean = false,
    val listExpenseModel: List<ExpenseModel> = listOf()
) {


    fun setSuccess(detailModel: DetailModel) = copy(
        detailModel = detailModel,
    )

    fun setSuccessExpense(listExpenseModel: List<ExpenseModel>) = copy(
        listExpenseModel = listExpenseModel,
    )

    fun showLoading(isLoading: Boolean) = copy(isLoading = isLoading)
}