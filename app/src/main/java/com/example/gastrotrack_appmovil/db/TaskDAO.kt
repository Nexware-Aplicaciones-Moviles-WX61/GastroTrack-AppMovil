package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.example.gastrotrack_appmovil.models.Task

@Dao
interface TaskDAO {
    @Insert
    fun insertTask(task: Task): Long

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE taskid = :id LIMIT 1")
    fun getTaskById(id: Int): Task

    @Delete
    fun deleteTask(vararg tasks: Task)

    @Update
    fun updateTask(task: Task)
}