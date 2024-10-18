package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gastrotrack_appmovil.models.Members

@Dao
interface MembersDAO {
    @Insert
    fun insertMember(member: Members)

    @Query("SELECT * FROM members")
    fun getAllMembers(): List<Members>

    @Delete
    fun deleteMember(vararg members: Members)


}
