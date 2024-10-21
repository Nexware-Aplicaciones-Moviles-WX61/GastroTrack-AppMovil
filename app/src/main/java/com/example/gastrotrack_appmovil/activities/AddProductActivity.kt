package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.communication.ProductApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.ProductDAO
import com.example.gastrotrack_appmovil.models.ECategory
import com.example.gastrotrack_appmovil.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etDateManufacture: EditText
    private lateinit var etDueDate: EditText
    private lateinit var etStock: EditText
    private lateinit var etState: EditText
    private lateinit var etImage: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var btnRegisterProduct: Button

    private lateinit var productDAO: ProductDAO
    private var selectedCategoryId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_product)

        etProductName = findViewById(R.id.etProductName)
        etDateManufacture = findViewById(R.id.etDateManufacture)
        etDueDate = findViewById(R.id.etExpirationDate)
        etStock = findViewById(R.id.etStock)
        etState = findViewById(R.id.etState)
        etImage = findViewById(R.id.etProdcutImage)
        spinnerCategory = findViewById(R.id.spTags)
        btnRegisterProduct = findViewById(R.id.btnAcceptProduct)
        val btnCancelUP = findViewById<Button>(R.id.btnCancelUP)

        val database = AppDatabase.getDatabase(this)
        productDAO = database.productDAO()

        setupCategorySpinner()

        btnRegisterProduct.setOnClickListener {
            registerProduct()
        }

        btnCancelUP.setOnClickListener {
            val intent = Intent(this, InventoryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupCategorySpinner() {
        val categories = ECategory.values()
        val categoryNames = categories.map { it.name }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCategoryId = categories[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCategoryId = -1
            }
        }
    }

    private fun registerProduct() {
        val productName = etProductName.text.toString().trim()
        val dateManufacture = etDateManufacture.text.toString().trim()
        val dueDate = etDueDate.text.toString().trim()
        val stock = etStock.text.toString().trim()
        val state = etState.text.toString().trim()
        val image = etImage.text.toString().trim()

        if (productName.isEmpty() || dateManufacture.isEmpty() || dueDate.isEmpty() || stock.isEmpty() || state.isEmpty() || image.isEmpty() || selectedCategoryId == -1) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val productApiResponse = ProductApiResponse(
            name = productName,
            categoryId = selectedCategoryId,
            dateManufacture = dateManufacture,
            dueDate = dueDate,
            stock = stock.toInt(),
            state = state,
            image = image
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productService = retrofit.create(ProductService::class.java)
        val call = productService.createProduct(productApiResponse)

        call.enqueue(object : Callback<ProductApiResponse> {
            override fun onResponse(call: Call<ProductApiResponse>, response: Response<ProductApiResponse>) {
                if (response.isSuccessful) {
                    val product = productApiResponse.toProduct()
                    productDAO.insertProduct(product)

                    Toast.makeText(this@AddProductActivity, "Producto registrado exitosamente", Toast.LENGTH_SHORT).show()

                    // Finaliza la actividad
                    finish()
                } else {
                    Toast.makeText(this@AddProductActivity, "Error al registrar producto: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductApiResponse>, t: Throwable) {
                Toast.makeText(this@AddProductActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}