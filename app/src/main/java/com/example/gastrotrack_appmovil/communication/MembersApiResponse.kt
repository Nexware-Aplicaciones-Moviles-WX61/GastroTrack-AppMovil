package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Members

data class MembersApiResponse(
    val id: Long,
    val name: String,
    val price: Long,
    val userId: Int
) {
    fun toMembers(): Members {
        return Members(
            id = id,
            name = name,
            price = price,
            userId = userId
        )
    }
}
