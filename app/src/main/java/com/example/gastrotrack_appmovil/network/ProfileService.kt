package com.example.gastrotrack_appmovil.network;

import androidx.room.Delete
import com.example.gastrotrack_appmovil.communication.ProductApiResponse
import com.example.gastrotrack_appmovil.communication.ProfileApiResponse;

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

public interface ProfileService {

    @POST("api/v1/profile")
    fun createProfile(@Body profile:ProfileApiResponse): Call<ProfileApiResponse>

    @GET("api/v1/profile")
    fun getProfile(): Call<List<ProfileApiResponse>>

    @PUT("api/v1/profile/{profileid}")
    fun updateProfile(@Path("profileid") profileId:Int, @Body profile: ProfileApiResponse): Call<ProfileApiResponse>

    @DELETE("api/v1/profile/{profileid}")
    fun deleteProduct(@Path("profileid") profileId:Int): Call<Void>
}
