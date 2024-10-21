package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "suppliers")
data class Supplier(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "supplier_name")
    val supplierName: String,
    @ColumnInfo(name = "restaurant_name")
    val restaunrantName: String,
    @ColumnInfo(name = "contact_email")
    val contactEmail: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "supplier_photo")
    val supplierPhoto: String
)