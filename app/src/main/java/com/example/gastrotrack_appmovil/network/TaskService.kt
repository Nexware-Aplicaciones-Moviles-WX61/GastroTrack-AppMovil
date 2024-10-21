package com.example.gastrotrack_appmovil.network

import com.example.gastrotrack_appmovil.communication.TaskApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskService {
    @POST("api/v1/task")
    fun createTask(@Body task: TaskApiResponse): Call<TaskApiResponse>
}