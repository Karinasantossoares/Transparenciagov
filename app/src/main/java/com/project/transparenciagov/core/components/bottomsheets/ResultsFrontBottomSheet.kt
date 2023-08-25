package com.project.transparenciagov.core.components.bottomsheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.transparenciagov.R
import com.project.transparenciagov.categoryfront.domain.model.ResultFrontCongressPersonModel
import com.project.transparenciagov.databinding.ResultFrontBottomSheetBinding

class ResultsFrontBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: ResultFrontBottomSheetBinding
    private val adapter by lazy { ResultFrontAdapter() }


    var openUrl: (String) -> Unit = {}
    var urlDoc: String = ""

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return BottomSheetDialog(requireContext(), theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultFrontBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnClickListeners()

    }

    private fun setupView() {
        with(binding) {
            recyclerResult.adapter = adapter
            loading.isVisible = true
            recyclerResult.isVisible = false
            button.isVisible = false
        }

    }

    private fun setOnClickListeners() {
        with(binding) {
            button.setOnClickListener {
                openUrl.invoke(urlDoc)
            }

        }
    }

    fun setupData(resultFront: ResultFrontCongressPersonModel) {
        with(binding) {
            loading.isVisible = false
            recyclerResult.isVisible = true
            button.isVisible = true
            urlDoc = resultFront.urlDocument
            title.text = resultFront.title
            adapter.listResults = resultFront.situation
        }
    }

    fun show(manager: FragmentManager) {
        if (!isAdded) {
            super.showNow(manager, "states")
        }
    }


}

