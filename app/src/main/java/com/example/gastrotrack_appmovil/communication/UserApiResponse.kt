package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.User

data class UserApiResponse(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val company: String
) {
    fun toUser(): User {
        return User(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            company = company
        )
    }
}