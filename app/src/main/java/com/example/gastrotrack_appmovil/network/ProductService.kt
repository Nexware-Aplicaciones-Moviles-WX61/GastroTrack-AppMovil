package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.ProductApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductService {
    @POST("api/v1/products")
    fun createProduct(@Body product: ProductApiResponse): Call<ProductApiResponse>
}