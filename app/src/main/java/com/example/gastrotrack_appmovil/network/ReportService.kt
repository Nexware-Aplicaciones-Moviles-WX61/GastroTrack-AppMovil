package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.ReportApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportService {
    @POST("api/v1/report")
    fun createReport(@Body report: ReportApiResponse): Call<ReportApiResponse>
}