package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.NotificationApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface NotificationService {
    @GET("api/v1/notification")
    fun getNotifications(): Call<List<NotificationApiResponse>>
}