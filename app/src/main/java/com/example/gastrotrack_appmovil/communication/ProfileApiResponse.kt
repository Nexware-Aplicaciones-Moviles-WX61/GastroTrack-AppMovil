package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Profile

class ProfileApiResponse(
    private var userId: Int? = null,
    private var bio: String? = null,
    private var profilePicture: String? = null
){
    fun toProfile(): Profile {
        return Profile(
            user = userId,
            bio = bio,
            picture = profilePicture
        )
    }
}