package com.project.transparenciagov.categoryfront.data.model

import com.google.gson.annotations.SerializedName

data class FrontCongressPersonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("titulo")
    val title: String
)