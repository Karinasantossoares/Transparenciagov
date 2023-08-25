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
import com.project.transparenciagov.databinding.StatesBottomSheetBinding
import com.project.transparenciagov.home.domain.model.StatesModel

class StatesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: StatesBottomSheetBinding
    private val adapter by lazy { StatesAdapter() }
    var list: List<StatesModel> = emptyList()
        set(value) {
            field = value
            if (isAdded && list.isNotEmpty()) {
                setupView()
                adapter.submitList(list)
            }
        }
    var confirmButtonSelected: (List<StatesModel>) -> Unit = {}
    var requestStates: () -> Unit = {}

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
        binding = StatesBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnClickListeners()

        if (list.isEmpty()) {
            requestStates.invoke()
        }
    }

    private fun setupView() {
        binding.recyclerPerson.adapter = adapter
        binding.progressLoader.isVisible = list.isEmpty()
        binding.recyclerPerson.isVisible = list.isNotEmpty()
    }

    private fun setOnClickListeners() {
        with(binding) {

            confirmButton.setOnClickListener {
                confirmButtonSelected.invoke(adapter.listItemSelected)
                dismiss()
            }
            closeButton.setOnClickListener {
                dismiss()
            }
        }
    }

    fun show(manager: FragmentManager) {
        if (!isAdded) {
            super.showNow(manager, "states")
        }
    }

    fun removeFilterForChips(currentList: MutableList<StatesModel>) {
        adapter.listItemSelected = currentList
    }


}

