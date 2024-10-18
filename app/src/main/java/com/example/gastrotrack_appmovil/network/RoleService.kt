package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.RoleApiResponse
import com.example.gastrotrack_appmovil.models.Role
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RoleService {
    @GET("api/v1/role")
    fun getRoles(): Call<List<RoleApiResponse>>
}