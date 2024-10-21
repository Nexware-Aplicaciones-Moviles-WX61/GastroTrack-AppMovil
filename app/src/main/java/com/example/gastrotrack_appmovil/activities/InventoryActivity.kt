package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.MainActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.ProductAdapter
import com.example.gastrotrack_appmovil.communication.ProductApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.models.Product
import com.example.gastrotrack_appmovil.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InventoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        val ibHomeReturnI = findViewById<ImageButton>(R.id.btnHomeReturnInventory)
        ibHomeReturnI.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val ibAdd = findViewById<ImageButton>(R.id.ibAdd)
        ibAdd.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rvProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadProducts()
    }

    private fun loadProducts() {
        val dao = AppDatabase.getDatabase(this).productDAO()
        val products = dao.getAllProducts()

        if (products.isNotEmpty()) {
            adapter = ProductAdapter(products.toMutableList(),
                { product -> editProduct(product) },
                { product -> deleteProduct(product) }
            )
            recyclerView.adapter = adapter
        } else {
            Toast.makeText(this, "No hay productos en el inventario.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editProduct(product: Product) {
        val intent = Intent(this, AddProductActivity::class.java)
        intent.putExtra("PRODUCT_ID", product.id)
        startActivity(intent)
    }

    private fun deleteProduct(product: Product) {
        val dao = AppDatabase.getDatabase(this).productDAO()
        dao.deleteProduct(product)
        Toast.makeText(this, "Producto ${product.name} eliminado del inventario", Toast.LENGTH_SHORT).show()
        loadProducts()
    }
}