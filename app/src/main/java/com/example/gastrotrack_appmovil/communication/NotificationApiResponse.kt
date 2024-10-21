package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Notification
import com.google.gson.annotations.SerializedName

data class NotificationApiResponse(
    @SerializedName("notificationId") val notificationId: Int,
    @SerializedName("notificationName") val notificationName: String,
    @SerializedName("notificationDescription") val notificationDescription: String
) {
    fun toNotification(): Notification {
        return Notification(notificationId, notificationName, notificationDescription)
    }
}