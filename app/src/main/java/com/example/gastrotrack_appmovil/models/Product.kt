package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "category_id")
    val categoryId: ECategory,  // Usamos el enum ECategory
    @ColumnInfo(name = "date_manufacture")
    val dateManufacture: String,
    @ColumnInfo(name = "due_date")
    val dueDate: String,
    @ColumnInfo(name = "stock")
    val stock: Int,
    @ColumnInfo(name = "state")
    val state: String,
    @ColumnInfo(name = "image")
    val image: String
)
