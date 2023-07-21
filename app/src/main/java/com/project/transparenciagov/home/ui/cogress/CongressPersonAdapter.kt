package com.project.transparenciagov.home.ui.cogress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.transparenciagov.R
import com.project.transparenciagov.databinding.ProfileCongressItemBinding
import com.project.transparenciagov.home.domain.model.CongressPerson
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class CongressPersonAdapter :
    RecyclerView.Adapter<CongressPersonAdapter.CategoryDebitViewHolder>() {

    var listCongressPerson: List<CongressPerson> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickItem : (CongressPerson) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDebitViewHolder {
        val binding = ProfileCongressItemBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryDebitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryDebitViewHolder, position: Int) {
        holder.bind(listCongressPerson[position])
    }

    override fun getItemCount(): Int = listCongressPerson.size


    inner class CategoryDebitViewHolder(val binding: ProfileCongressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CongressPerson) {
            with(binding) {
                binding.container.setOnClickListener {
                    onClickItem.invoke(data)
                }
                nameCongressPerson.text = data.name
                brokenPerson.text = data.siglaPartido
                Picasso.get()
                    .load(data.urlFoto)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageCongressPerson, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {
                            Picasso.get()
                                .load(data.urlFoto)
                                .error(R.drawable.ic_person)
                                .into(imageCongressPerson)
                        }
                    })
            }
        }


    }
}