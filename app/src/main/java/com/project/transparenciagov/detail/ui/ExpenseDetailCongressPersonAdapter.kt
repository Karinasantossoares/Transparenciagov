package com.project.transparenciagov.detail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.transparenciagov.databinding.DetailProfileCongressItemBinding
import com.project.transparenciagov.detail.domain.model.ExpenseModel

class ExpenseDetailCongressPersonAdapter :
    RecyclerView.Adapter<ExpenseDetailCongressPersonAdapter.CategoryDebitViewHolder>() {

    var lisExpense: List<ExpenseModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDebitViewHolder {
        val binding = DetailProfileCongressItemBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryDebitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryDebitViewHolder, position: Int) {
        holder.bind(lisExpense[position])
    }

    override fun getItemCount(): Int = lisExpense.size


    inner class CategoryDebitViewHolder(val binding: DetailProfileCongressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ExpenseModel) {
            with(binding) {
                textDetailExpense.text = data.expenseType
                tvDateList.text = data.documentDate
                tvPriceList.text = data.documentValue
            }
        }


    }
}