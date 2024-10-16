package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.MembersApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {
    @POST("api/v1/membrers")
    fun createMember(@Body member: MembersApiResponse): Call<MembersApiResponse>
}
