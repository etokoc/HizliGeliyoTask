package com.etoertugrul.hizligeliyotask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.databinding.ItemRecylerviewProductBinding
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.models.ProductResponseItem
import java.util.*

class ProductRecylerViewAdapter(var context: Context, var productList: ProductResponse) :
    RecyclerView.Adapter<ProductRecylerViewAdapter.MyViewHolder>(), Filterable {
    var productFilterList = ProductResponse()

    init {
        productFilterList = productList
    }

    inner class MyViewHolder(val binding: ItemRecylerviewProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(get: ProductResponseItem) {
            binding.itemTitle.text = get.title
            binding.itemPrice.text = context.getString(R.string.price_tag, get.price.toString())

            //Image download and push to imageView
            Glide.with(context)
                .load(get.image)
                .skipMemoryCache(true)
                .into(binding.itemImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecylerviewProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productFilterList[position])
    }

    override fun getItemCount(): Int {
        return productFilterList.size
    }

    /*
    item search with getFilter func.
    if edittext is null, change to normal list with filter list.
    if searched text contains that is (description,category,title) add to resultlist.
    resultlist is new productfilter list.
    * */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                //wanted post
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    productFilterList = productList
                } else {
                    val resultList = ProductResponse()
                    for (row in productList) {
                        if (row.title.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT)) || row.description.toLowerCase(
                                Locale.ROOT
                            )
                                .contains(charSearch.toLowerCase(Locale.ROOT)) || row.category.toLowerCase(
                                Locale.ROOT
                            )
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    productFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productFilterList = results?.values as ProductResponse
                notifyDataSetChanged()
            }

        }
    }
}