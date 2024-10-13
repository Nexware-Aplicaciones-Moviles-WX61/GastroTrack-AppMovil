package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.UserApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("api/v1/user")
    fun createdUser(@Body user: UserApiResponse): Call<UserApiResponse>
}