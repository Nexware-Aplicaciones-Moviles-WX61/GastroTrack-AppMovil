package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.SupplierApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SupplierService {
    @POST("api/v1/supplier")
    fun createSupplier(@Body supplier: SupplierApiResponse): Call<SupplierApiResponse>
}