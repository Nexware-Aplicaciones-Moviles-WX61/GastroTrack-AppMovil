// NotificationService.kt
package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.models.Notification
import retrofit2.Call
import retrofit2.http.*

interface NotificationService {

    @POST("/api/v1/notification")
    fun createNotification(@Body notification: Notification): Call<Void>

    @GET("/api/v1/notification")
    fun getNotifications(): Call<List<Notification>>

    @GET("/api/v1/notification/{notificationId}")
    fun getNotification(@Path("notificationId") id: Long): Call<Notification>

    @PUT("/api/v1/notification/{notificationId}")
    fun updateNotification(
        @Path("notificationId") id: Long,
        @Body notification: Notification
    ): Call<Void>

    @DELETE("/api/v1/notification/{notificationId}")
    fun deleteNotification(@Path("notificationId") id: Long): Call<Void>
}