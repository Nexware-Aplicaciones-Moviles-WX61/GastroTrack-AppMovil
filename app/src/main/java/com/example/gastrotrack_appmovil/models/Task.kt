package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo
    val name: String?,

    @ColumnInfo
    val description: String?,

    @ColumnInfo
    val date: String?
)