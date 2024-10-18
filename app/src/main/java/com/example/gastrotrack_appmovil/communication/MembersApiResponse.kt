package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Members

data class MembersApiResponse (
    val memberName: String,
    val description: String,
    val photo: String,
    val roleId: Int
){
    fun toMember(): Members {
        return Members(
            memberName = memberName,
            description = description,
            photo = photo,
            roleId = roleId
        )
    }
}