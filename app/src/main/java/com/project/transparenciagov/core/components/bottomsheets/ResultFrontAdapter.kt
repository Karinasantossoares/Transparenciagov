package com.project.transparenciagov.core.components.bottomsheets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.transparenciagov.categoryfront.domain.model.ItemSituation
import com.project.transparenciagov.databinding.ResultItemBinding

class ResultFrontAdapter :
    RecyclerView.Adapter<ResultFrontAdapter.ResultViewHolder>() {

    var listResults: List<ItemSituation> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(listResults[position])
    }

    override fun getItemCount(): Int = listResults.size


    inner class ResultViewHolder(private val binding: ResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemSituation) {
            binding.title.text = data.title
            binding.date.text = data.date
        }
    }


}
