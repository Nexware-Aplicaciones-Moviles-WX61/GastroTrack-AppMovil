package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gastrotrack_appmovil.models.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM products")
    fun getAll(): List<Product>

    @Insert
    fun insertOne(product: Product)

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)

}