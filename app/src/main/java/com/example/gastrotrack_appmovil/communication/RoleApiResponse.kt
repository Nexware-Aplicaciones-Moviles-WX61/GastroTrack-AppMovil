package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Role
import com.google.gson.annotations.SerializedName


data class RoleApiResponse (
    @SerializedName("roleId") val roleId: Int,
    @SerializedName("roleName") val roleName: String
){
    fun toRole(): Role {
        return Role(
            roleId,
            roleName
        )
    }
}