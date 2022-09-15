package com.etoertugrul.hizligeliyotask.api

import com.etoertugrul.hizligeliyotask.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET


interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): Response<ProductResponse>
}