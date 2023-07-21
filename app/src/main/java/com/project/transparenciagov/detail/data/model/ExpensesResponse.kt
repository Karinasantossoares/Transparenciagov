package com.project.transparenciagov.detail.data.model

import com.google.gson.annotations.SerializedName

data class ExpensesResponse(
    @SerializedName("tipoDespesa")
    val expenseType : String,
    @SerializedName("valorDocumento")
    val documentValue : String,
    @SerializedName("dataDocumento")
    val documentDate: String

)