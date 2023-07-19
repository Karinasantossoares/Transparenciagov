package com.project.transparenciagov.home.ui.cogress.action

import android.graphics.drawable.Drawable
import com.project.transparenciagov.core.util.BaseEvent
import com.project.transparenciagov.home.domain.model.StatesModel

sealed class CongressPersonAction : BaseEvent() {
    data class ShowError(
        val message: String,
        val imageError: Drawable?
    ) : CongressPersonAction()

    data class ShowToast(val message: String) : CongressPersonAction()
    object OpenBottomSheet : CongressPersonAction()
}