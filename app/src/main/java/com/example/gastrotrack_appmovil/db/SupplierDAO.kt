package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.gastrotrack_appmovil.models.Supplier
import com.example.gastrotrack_appmovil.models.Task
import com.example.gastrotrack_appmovil.models.User

@Dao
interface SupplierDAO {
    @Insert
    fun insertSupplier(supplier: Supplier)

    @Query("SELECT * FROM suppliers")
    fun getAllSuppliers(): List<Supplier>
}