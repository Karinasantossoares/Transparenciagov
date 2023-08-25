package com.project.transparenciagov.categoryfront.presentation.action

import android.graphics.drawable.Drawable
import com.project.transparenciagov.categoryfront.domain.model.ResultFrontCongressPersonModel
import com.project.transparenciagov.core.util.BaseEvent
import com.project.transparenciagov.detail.ui.action.DetailCongressPersonAction

sealed class FrontAction : BaseEvent() {
    data class ShowError(
        val message: String,
        val imageError: Drawable?
    ) : FrontAction()

    object OpenBottomSheet : FrontAction()
    data class UpdateDataBottomSheet(val resultFrontCongress: ResultFrontCongressPersonModel) :
        FrontAction()

}