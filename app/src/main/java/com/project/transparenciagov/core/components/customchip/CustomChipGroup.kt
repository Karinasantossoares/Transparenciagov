package com.project.transparenciagov.core.components.customchip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.chip.Chip
import com.project.transparenciagov.databinding.CustomChipGroupBinding
import com.project.transparenciagov.home.domain.model.StatesModel

class CustomChipGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {


    private var listUf: MutableList<StatesModel> = mutableListOf()
    private val binding by lazy {
        CustomChipGroupBinding.inflate(
            LayoutInflater.from(context), this,
            false
        )
    }

    var onRemoveItem: (List<StatesModel>) -> Unit = {}


    init {
        addView(binding.root)
    }

    fun bind(currentList: MutableList<StatesModel>) {
        this.listUf = currentList
        binding.chipGroup.removeAllViews()
        listUf.forEach {value->
            val chip = Chip(context).apply {
                text = value.acronym
                isCloseIconVisible = true
            }
            binding.chipGroup.addView(chip)
            chip.setOnCloseIconClickListener {
                binding.chipGroup.removeView(chip)
                listUf.remove(value)
                onRemoveItem.invoke(listUf)
            }
        }
    }
}
