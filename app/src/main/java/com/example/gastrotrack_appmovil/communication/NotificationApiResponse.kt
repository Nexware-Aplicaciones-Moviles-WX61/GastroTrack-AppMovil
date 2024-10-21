// NotificationApiResponse.kt
package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Notification

data class NotificationApiResponse(
    val id: Long,
    val title: String,
    val description: String
) {
    fun toNotification(): Notification {
        return Notification(
            id = id,
            title = title,
            description = description
        )
    }
}