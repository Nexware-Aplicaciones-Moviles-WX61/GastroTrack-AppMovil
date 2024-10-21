package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Supplier

data class SupplierApiResponse(
    val supplierName: String,
    val restaunrantName: String,
    val contactEmail: String,
    val phone: String,
    val supplierPhoto: String
) {
    fun toSupplier(): Supplier {
        return Supplier(
            supplierName = supplierName,
            restaunrantName = restaunrantName,
            contactEmail = contactEmail,
            phone = phone,
            supplierPhoto = supplierPhoto
        )
    }
}