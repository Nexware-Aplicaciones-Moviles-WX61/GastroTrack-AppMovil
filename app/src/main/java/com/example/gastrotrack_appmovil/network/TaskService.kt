package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.models.Task
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskService {
    @GET("task")
    fun getAll(): Call<List<Task>>

    @POST("task")
    fun insertOne(@Body task: Task): Call<Task>

    @PUT("task/{id}")
    fun update(@Path("id") id: Int, @Body task: Task): Call<Task>

    @DELETE("task/{id}")
    fun delete(@Path("id") id: Int): Call<Void>
}