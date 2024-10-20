package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
class Product (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "product_name")
    var name: String? = null,
    @ColumnInfo(name="categoryId")
    var categoryId: Int? = null ,// Relacionado con la tabla Category
    @ColumnInfo(name = "dateManufacture")
    var dateManufacture: String? = null,
    @ColumnInfo(name="dueDate")
    var dueDate: String? = null,
    @ColumnInfo(name = "stock")
    var stock: Int? = null,
    @ColumnInfo(name="state")
    var state: String? = null,
    @ColumnInfo(name="image")
    var image: String? = null
)