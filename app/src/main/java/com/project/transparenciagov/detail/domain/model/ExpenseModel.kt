package com.project.transparenciagov.detail.domain.model

import com.google.gson.annotations.SerializedName

data class ExpenseModel(
    val expenseType : String,
    val documentValue : String,
    val documentDate: String
)