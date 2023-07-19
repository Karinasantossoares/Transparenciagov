package com.project.transparenciagov.core.model

import com.google.gson.annotations.SerializedName


class BaseModel<out T>(@SerializedName("dados") val dados: T) {
}
