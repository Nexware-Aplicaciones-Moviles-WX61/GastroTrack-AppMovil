package com.example.gastrotrack_appmovil.network

import androidx.room.Delete
import com.example.gastrotrack_appmovil.communication.ProductApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductService {
    @POST("api/v1/products")
    fun createProduct(@Body product: ProductApiResponse): Call<ProductApiResponse>
}