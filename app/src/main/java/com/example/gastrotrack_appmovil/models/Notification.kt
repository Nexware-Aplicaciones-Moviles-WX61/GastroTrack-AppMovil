package com.example.gastrotrack_appmovil.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class Notification(
    @PrimaryKey val notificationId: Int,
    val notificationName: String,
    val notificationDescription: String
)