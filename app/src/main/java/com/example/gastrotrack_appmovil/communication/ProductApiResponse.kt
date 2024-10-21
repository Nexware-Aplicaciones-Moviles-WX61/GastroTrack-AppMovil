package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.ECategory
import com.example.gastrotrack_appmovil.models.Product

class ProductApiResponse (
    val name: String,
    val categoryId: Int,
    val dateManufacture: String,
    val dueDate: String,
    val stock: Int,
    val state: String,
    val image: String
){
    fun toProduct(): Product {
        return Product(
            name = name,
            categoryId = ECategory.fromId(categoryId),  // Convertimos el ID numérico a ECategory
            dateManufacture = dateManufacture,
            dueDate = dueDate,
            stock = stock,
            state = state,
            image = image
        )
    }
}

