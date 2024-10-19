package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gastrotrack_appmovil.models.Task

@Dao
interface TaskDAO {
    @Insert
    fun insertOne(task: Task)

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}