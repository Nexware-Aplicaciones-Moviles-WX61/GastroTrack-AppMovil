// NotificationDAO.kt
package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gastrotrack_appmovil.models.Notification

@Dao
interface NotificationDAO {
    @Insert
    suspend fun insert(notification: Notification): Long

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<Notification>

    @Delete
    suspend fun deleteNotification(vararg notifications: Notification)

    @Query("SELECT * FROM notifications WHERE id = :notificationId")
    suspend fun getNotificationById(notificationId: Long): Notification?
}