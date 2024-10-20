package com.example.gastrotrack_appmovil.db;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gastrotrack_appmovil.models.Profile

@Dao
interface ProfileDAO {

    @Query("SELECT * FROM profile")
    fun getAll(): List<Profile>

    @Insert
    fun insertOne(profile: Profile)

    @Delete
    fun delete(profile: Profile)

    @Update
    fun update(profile: Profile)
}