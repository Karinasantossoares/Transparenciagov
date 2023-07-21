package com.project.transparenciagov.detail.ui.action

import android.graphics.drawable.Drawable
import com.project.transparenciagov.core.util.BaseEvent

sealed class DetailCongressPersonAction : BaseEvent() {
    data class ShowError(
        val message: String,
        val imageError: Drawable?
    ) : DetailCongressPersonAction()

    data class ShowToast(val message: String) : DetailCongressPersonAction()
}