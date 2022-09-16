package com.etoertugrul.hizligeliyotask.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoertugrul.hizligeliyotask.api.ProductService
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    //Product Encapsulate
    private var _products = MutableLiveData<Response<ProductResponse>>()
    val products: LiveData<Response<ProductResponse>>
        get() = _products

    fun getData() {
        viewModelScope.launch {
            try {
                _products.value = ProductService.api.getAllProducts()
            } catch (e: Exception) {
                Log.i("Exception", "getData: Coroutines =  " + e.localizedMessage)
            }
        }
    }

    fun getCategories(): ArrayList<String> {
        val data = _products.value as Response<ProductResponse>
        val categoriesList = ArrayList<String>()
        data.body().let {
            it?.forEach { productItem ->
                if (!categoriesList.contains(productItem.category))
                    categoriesList.add(productItem.category)
            }
        }
        return categoriesList
    }
}
