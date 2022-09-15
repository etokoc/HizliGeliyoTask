package com.etoertugrul.hizligeliyotask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etoertugrul.hizligeliyotask.databinding.ItemRecylerviewBinding
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.models.ProductResponseItem

class CustomRecylerViewAdapter(var list: ProductResponse) :
    RecyclerView.Adapter<CustomRecylerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemRecylerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(get: ProductResponseItem) {
            binding.itemTitle.text = get.title
            binding.itemDescription.text = get.description
            binding.itemPrice.text = get.price.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecylerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}