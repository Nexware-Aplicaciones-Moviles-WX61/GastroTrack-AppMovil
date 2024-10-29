package com.example.gastrotrack_appmovil.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.ProductDAO
import com.example.gastrotrack_appmovil.models.ECategory
import com.example.gastrotrack_appmovil.models.Product

class UpdateProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etDateManufacture: EditText
    private lateinit var etDueDate: EditText
    private lateinit var etStock: EditText
    private lateinit var etState: EditText
    private lateinit var etImage: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var btnUpdateProduct: Button
    private lateinit var productDAO: ProductDAO
    private var productToEdit: Product? = null
    private lateinit var categoryList: List<String>

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
        btnUpdateProduct = findViewById(R.id.btnAcceptProduct)
        val btnCancelUP = findViewById<Button>(R.id.btnCancelUP)

        val database = AppDatabase.getDatabase(this)
        productDAO = database.productDAO()

        categoryList = ECategory.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        if (productId != -1) {
            productToEdit = productDAO.getProductById(productId)
            productToEdit?.let { populateFields(it) }
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnUpdateProduct.setOnClickListener {
            updateProduct()
        }

        btnCancelUP.setOnClickListener {
            finish()
        }
    }

    private fun populateFields(product: Product) {
        etProductName.setText(product.name)
        etDateManufacture.setText(product.dateManufacture)
        etDueDate.setText(product.dueDate)
        etStock.setText(product.stock.toString())
        etState.setText(product.state)
        etImage.setText(product.image)

        val categoryIndex = categoryList.indexOf(product.categoryId.name)
        spinnerCategory.setSelection(categoryIndex)
    }

    private fun updateProduct() {
        val updatedProduct = productToEdit?.copy(
            name = etProductName.text.toString().trim(),
            dateManufacture = etDateManufacture.text.toString().trim(),
            dueDate = etDueDate.text.toString().trim(),
            stock = etStock.text.toString().trim().toInt(),
            state = etState.text.toString().trim(),
            image = etImage.text.toString().trim(),
            categoryId = ECategory.valueOf(spinnerCategory.selectedItem.toString())
        )

        if (updatedProduct != null) {
            productDAO.updateProduct(updatedProduct)
            Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Error al actualizar producto", Toast.LENGTH_SHORT).show()
        }
    }
}