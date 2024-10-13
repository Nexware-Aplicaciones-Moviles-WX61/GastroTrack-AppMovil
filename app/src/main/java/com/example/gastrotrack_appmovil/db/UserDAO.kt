package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gastrotrack_appmovil.models.User


@Dao
interface UserDAO {
    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Delete
    fun deleteUser(vararg users: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun getUserByEmail(email: String): User
}