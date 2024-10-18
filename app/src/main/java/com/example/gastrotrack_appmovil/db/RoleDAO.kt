package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gastrotrack_appmovil.models.Role

@Dao
interface RoleDAO {
    @Query("SELECT * FROM roles")
    fun getAllRoles(): List<Role>
}