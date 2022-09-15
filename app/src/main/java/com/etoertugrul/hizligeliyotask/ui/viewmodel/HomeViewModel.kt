package com.etoertugrul.hizligeliyotask.ui.viewmodel

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
            }
        }
    }
}
