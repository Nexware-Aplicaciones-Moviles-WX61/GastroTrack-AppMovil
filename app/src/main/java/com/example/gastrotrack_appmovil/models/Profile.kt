package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profile")
class Profile(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "userId")
    var user: Int? = null,
    @ColumnInfo(name = "bio")
    var bio: String? = null,
    @ColumnInfo(name = "profilePicture")
    var picture: String? = null
)