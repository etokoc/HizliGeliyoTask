package com.etoertugrul.hizligeliyotask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.databinding.ItemRecylerviewBinding
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.models.ProductResponseItem

class CustomRecylerViewAdapter(var context: Context, var list: ProductResponse) :
    RecyclerView.Adapter<CustomRecylerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemRecylerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(get: ProductResponseItem) {
            binding.itemTitle.text = get.title
            binding.itemPrice.text = context.getString(R.string.price_tag, get.price.toString())

            Glide.with(context)
                .load(get.image)
                .skipMemoryCache(true)
                .into(binding.itemImage)
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