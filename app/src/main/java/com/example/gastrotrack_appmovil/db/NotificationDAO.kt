package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gastrotrack_appmovil.models.Notification

@Dao
interface NotificationDAO {
    @Insert
    fun insertNotification(notification: Notification)

    @Query("SELECT * FROM notifications")
    fun getAllNotifications(): List<Notification>

    @Query("SELECT * FROM notifications WHERE notificationId = :notificationId")
    fun getNotificationById(notificationId: Int): Notification?
}