package com.project.transparenciagov.detail.mapper

import com.project.transparenciagov.detail.data.model.ExpensesResponse
import com.project.transparenciagov.detail.domain.model.ExpenseModel

class ExpenseMapper {

    fun parseToEntity(item: ExpensesResponse): ExpenseModel =
        ExpenseModel(
            expenseType = item.expenseType,
            documentDate = item.documentDate,
            documentValue = item.documentValue
        )
}