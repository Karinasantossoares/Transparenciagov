package com.project.transparenciagov.categoryfront.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.transparenciagov.R
import com.project.transparenciagov.categoryfront.domain.model.FrontCongressPersonModel
import com.project.transparenciagov.databinding.FrontItemBinding


class FrontCongressPersonAdapter :
    RecyclerView.Adapter<FrontCongressPersonAdapter.FrontViewHolder>() {

    var onKnowMore: (Int) -> Unit = {}
    var listFront: List<FrontCongressPersonModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrontViewHolder {
        val binding = FrontItemBinding.inflate(LayoutInflater.from(parent.context))
        return FrontViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FrontViewHolder, position: Int) {
        holder.bind(listFront[position])
    }

    override fun getItemCount(): Int = listFront.size


    inner class FrontViewHolder(val binding: FrontItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FrontCongressPersonModel) {
            with(binding) {
                button.text = button.context.getString(R.string.know_more_text)
                title.text = data.title
                button.setOnClickListener {
                    onKnowMore.invoke(data.id)
                }
            }
        }


    }
}