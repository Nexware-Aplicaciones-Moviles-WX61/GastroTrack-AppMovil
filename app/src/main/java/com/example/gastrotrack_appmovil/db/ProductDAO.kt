package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gastrotrack_appmovil.models.Product

@Dao
interface ProductDAO {
    @Insert
    fun insertProduct(product: Product): Long

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Delete
    fun deleteProduct(vararg products: Product)

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    fun getProductById(id: Int): Product

    @Update
    fun updateProduct(product: Product): Int
}