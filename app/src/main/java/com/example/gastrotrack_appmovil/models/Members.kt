package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class Members (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "member_name")
    val memberName: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "role_id")
    val roleId: Int // Relacionado con la tabla Role
)