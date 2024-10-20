package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Product

class ProductApiResponse (
    private var name: String? = null,
    private var categoryId: Int? = null,
    private var dateManufacture: String? = null,
    private var dueDate: String? = null,
    private var stock: Int? = null,
    private var state: String? = null,
    private var image: String? = null
){
    fun toProduct(): Product {
        return Product(
            name = name,
            categoryId = categoryId,
            dateManufacture = dateManufacture,
            dueDate = dueDate,
            stock = stock,
            state = state,
            image = image
        )
    }


}

