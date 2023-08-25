package com.project.transparenciagov.categoryfront.data.model

import com.google.gson.annotations.SerializedName

data class ResultFrontCongressPersonResponse(
    @SerializedName("situacao")
    val situation: String,
    @SerializedName("urlDocumento")
    val urlDocument: String,
    @SerializedName("titulo")
    val title: String

)