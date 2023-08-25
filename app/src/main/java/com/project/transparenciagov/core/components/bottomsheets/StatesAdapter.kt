package com.project.transparenciagov.core.components.bottomsheets

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.transparenciagov.R
import com.project.transparenciagov.databinding.StatesItemsBinding
import com.project.transparenciagov.home.domain.model.StatesModel

class StatesAdapter :
    ListAdapter<StatesModel, StatesAdapter.StatesViewHolder>(DiffUtilStates()) {

    var listItemSelected: MutableList<StatesModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatesViewHolder {
        val binding = StatesItemsBinding.inflate(LayoutInflater.from(parent.context))
        return StatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class StatesViewHolder(private val binding: StatesItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: StatesModel) {
            with(binding) {
                stateTextView.text = data.name
                siglaUfTextView.text = data.acronym

                if (listItemSelected.contains(data)) {
                    stateTextView.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    siglaUfTextView.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.container.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.primary
                        )
                    )
                } else {
                    stateTextView.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.black
                        )
                    )
                    siglaUfTextView.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.black
                        )
                    )
                    binding.container.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.background_shimmer
                        )
                    )

                }

                container.setOnClickListener {
                    if (listItemSelected.contains(data)) {
                        listItemSelected.remove(data)
                    } else {
                        listItemSelected.add(data)
                    }
                    notifyItemChanged(adapterPosition)
                }

            }
        }
    }
}

private class DiffUtilStates : DiffUtil.ItemCallback<StatesModel>() {

    override fun areItemsTheSame(oldItem: StatesModel, newItem: StatesModel): Boolean {
        return oldItem.cod == newItem.cod
    }

    override fun areContentsTheSame(oldItem: StatesModel, newItem: StatesModel): Boolean {
        return oldItem == newItem
    }
}