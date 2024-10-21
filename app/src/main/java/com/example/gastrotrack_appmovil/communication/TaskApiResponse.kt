package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Task

data class TaskApiResponse(
    val taskName: String,
    val taskDescription: String,
    val taskDate: String
) {
    fun toTask(): Task {
        return Task(
            taskName = taskName,
            taskDescription = taskDescription,
            taskDate = taskDate
        )
    }
}