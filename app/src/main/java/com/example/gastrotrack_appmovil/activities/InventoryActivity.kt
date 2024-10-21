package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.MainActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.ProductAdapter
import com.example.gastrotrack_appmovil.communication.ProductApiResponse
import com.example.gastrotrack_appmovil.models.Product
import com.example.gastrotrack_appmovil.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InventoryActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private lateinit var btnHomeReturnInventory: ImageButton
    private lateinit var btnLogOutInventory: ImageButton
    private lateinit var btnNotificationInventory: ImageButton
    private lateinit var btnInventoryInventory: ImageButton
    private lateinit var btnTeamInventory: ImageButton
    private lateinit var ibAdd: ImageButton
    private lateinit var btnSearchInventory: ImageButton
    private lateinit var etSearchInventory: EditText
    private lateinit var rvProducts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        btnHomeReturnInventory = findViewById(R.id.btnHomeReturnInventory)
        btnLogOutInventory = findViewById(R.id.btnLogOutInventory)
        btnNotificationInventory = findViewById(R.id.btnNotificationInventory)
        btnInventoryInventory = findViewById(R.id.btnInventoryInventory)
        btnTeamInventory = findViewById(R.id.btnTeamInventory)
        btnSearchInventory = findViewById(R.id.btnSearchInventory)
        ibAdd = findViewById(R.id.ibAdd)
        etSearchInventory = findViewById(R.id.etSearchInventory)
        rvProducts = findViewById(R.id.rvProducts)


        btnHomeReturnInventory.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }
        btnLogOutInventory.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnNotificationInventory.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        btnInventoryInventory.setOnClickListener {
            val intent = Intent(this, InventoryActivity::class.java)
            startActivity(intent)
        }
        btnTeamInventory.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }
        ibAdd.setOnClickListener {
            var tvAddProduct = findViewById<TextView>(R.id.tvAddUpdateProduct)
            tvAddProduct.text = "Add Product"
            val intent = Intent(this, UpdateProductActivity::class.java)
            startActivity(intent)
        }

        loadProducts { products ->
            rvProducts.adapter = ProductAdapter(products, this)
            rvProducts.layoutManager = LinearLayoutManager(this@InventoryActivity)
        }



    }

    override fun onResume() {
        super.onResume()
        btnSearchInventory.setOnClickListener {
            val search = etSearchInventory.text.toString()


        }
    }

    private fun loadProducts(onComplete: (List<Product>) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val inventoryApiService: ProductService = retrofit.create(ProductService::class.java)

        val request = inventoryApiService.getProducts()

        request.enqueue(object : Callback<List<ProductApiResponse>> {
            override fun onResponse(call: Call<List<ProductApiResponse>>, response: Response<List<ProductApiResponse>>) {
                if (response.isSuccessful) {
                    val productApiResponseList: List<ProductApiResponse>? = response.body()
                    if (productApiResponseList != null) {
                        val productList = mutableListOf<Product>()
                        productApiResponseList.forEach {
                            productList.add(it.toProduct())
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<ProductApiResponse>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })



    }

    override fun onDeleteClick(products: Product) {

    }

    override fun onEditClick(products: Product) {

    }


}
