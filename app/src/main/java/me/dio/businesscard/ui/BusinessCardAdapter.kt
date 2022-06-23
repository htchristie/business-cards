package me.dio.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.businesscard.data.BusinessCard
import me.dio.businesscard.databinding.CardItemBinding

class BusinessCardAdapter: ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: CardItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.tvCardName.text = item.name
            binding.tvCardSurname.text = item.surname
            binding.tvCardCompany.text = item.organization
            binding.tvCardJobTitle.text = item.jobTitle
            binding.tvCardPhone.text = item.phone
            binding.tvCardEmail.text = item.email

            binding.cardContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem.id == newItem.id
}