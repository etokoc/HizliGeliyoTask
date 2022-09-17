package com.etoertugrul.hizligeliyotask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.databinding.ItemRecylerviewCategoriesBinding

class FilterRecylerViewAdapter(
    var context: Context, var categoriesList: ArrayList<String>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<FilterRecylerViewAdapter.MyViewHolder>() {

    var selectedItemPosition = -1

    inner class MyViewHolder(val binding: ItemRecylerviewCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(get: String, itemClickListener: OnItemClickListener) {
            binding.itemCategoryTitle.text = get
            binding.root.setOnClickListener {
                selectedItemPosition = adapterPosition
                itemClickListener.onItemClicked(get)
                notifyDataSetChanged()
            }
            //selected row change background
            if (selectedItemPosition == adapterPosition)
                binding.itemBackground.setBackgroundColor(context.getColor(R.color.button_background_yellow_color))
            else
                binding.itemBackground.setBackgroundColor(context.getColor(R.color.white))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecylerviewCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categoriesList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    interface OnItemClickListener {
        fun onItemClicked(string: String)
    }
}