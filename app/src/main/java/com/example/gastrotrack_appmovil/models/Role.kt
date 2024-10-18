package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roles")
data class Role (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "role_name")
    val roleName: String
)