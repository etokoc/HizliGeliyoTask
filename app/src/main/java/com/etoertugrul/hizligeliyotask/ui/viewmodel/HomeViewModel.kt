package com.etoertugrul.hizligeliyotask.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoertugrul.hizligeliyotask.api.ProductService
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.util.EnumSorted
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    //Product Encapsulate
    private var _products = MutableLiveData<Response<ProductResponse>>()
    val products: LiveData<Response<ProductResponse>>
        get() = _products

    private lateinit var productResponse: Response<ProductResponse>

    fun getData() {
        viewModelScope.launch {
            try {
                _products.value = ProductService.api.getAllProducts()
                productResponse = _products.value as Response<ProductResponse>
            } catch (e: Exception) {
                Log.i("Exception", "getData: Coroutines =  " + e.localizedMessage)
            }
        }
    }

    fun getCategories(): ArrayList<String> {
        val categoriesList = ArrayList<String>()
        productResponse.body().let {
            it?.forEach { productItem ->
                if (!categoriesList.contains(productItem.category))
                    categoriesList.add(productItem.category)
            }
        }
        return categoriesList
    }

    //first desc sorting and if sorted cheap to expensive make productList reverse.
    fun sortedList(enum: EnumSorted, productList: ProductResponse): ProductResponse {
        productList.sortWith(Comparator { lhs, rhs ->
            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            if (lhs.price > rhs.price) -1 else if (lhs.id < rhs.id) 1 else 0
        })
        if (enum == EnumSorted.CHEAP_TO_EXPENSIVE)
        {
            productList.reverse()
        }

        return productList
    }
}
