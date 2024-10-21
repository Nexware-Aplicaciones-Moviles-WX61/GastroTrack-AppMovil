package com.example.gastrotrack_appmovil.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.communication.ReportApiResponse
import com.example.gastrotrack_appmovil.communication.SupplierApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.ReportDAO
import com.example.gastrotrack_appmovil.db.SupplierDAO
import com.example.gastrotrack_appmovil.network.ReportService
import com.example.gastrotrack_appmovil.network.SupplierService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddSupplierActivity : AppCompatActivity() {

    private lateinit var etSupplierName: EditText
    private lateinit var etRestaurantName: EditText
    private lateinit var etContactEmail: EditText
    private lateinit var etPhoneProvider: EditText
    private lateinit var etSupplierPhoto: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnAccept: Button

    private lateinit var supplierDAO: SupplierDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_provider)

        etSupplierName = findViewById(R.id.etSupplierName)
        etRestaurantName = findViewById(R.id.etRestaurantName)
        etContactEmail = findViewById(R.id.etContactEmail)
        etPhoneProvider = findViewById(R.id.etPhoneProvider)
        etSupplierPhoto = findViewById(R.id.etSupplierPhoto)
        btnCancel = findViewById(R.id.button)
        btnAccept = findViewById(R.id.button6)

        val database = AppDatabase.getDatabase(this)
        supplierDAO = database.supplierDAO()

        btnCancel.setOnClickListener { finish() }

        btnAccept.setOnClickListener { registerSupplier() }
    }

    private fun registerSupplier() {
        val supplierName = etSupplierName.text.toString().trim()
        val restaunrantName = etRestaurantName.text.toString().trim()
        val contactEmail = etContactEmail.text.toString().trim()
        val phoneProvider = etPhoneProvider.text.toString().trim()
        val supplierPhoto = etSupplierPhoto.text.toString().trim()

        if (supplierName.isEmpty() || restaunrantName.isEmpty() || contactEmail.isEmpty() || phoneProvider.isEmpty() || supplierPhoto.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val supplierApiResponse = SupplierApiResponse(
            supplierName = supplierName,
            restaunrantName = restaunrantName,
            contactEmail = contactEmail,
            phone = phoneProvider,
            supplierPhoto = supplierPhoto
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val supplierService = retrofit.create(SupplierService::class.java)
        val call = supplierService.createSupplier(supplierApiResponse)

        call.enqueue(object : Callback<SupplierApiResponse> {
            override fun onResponse(call: Call<SupplierApiResponse>, response: Response<SupplierApiResponse>) {
                if (response.isSuccessful) {
                    val supplier = supplierApiResponse.toSupplier()
                    supplierDAO.insertSupplier(supplier)

                    Toast.makeText(this@AddSupplierActivity, "Proveedor registrado exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AddSupplierActivity, "Error al registrar proveedor: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SupplierApiResponse>, t: Throwable) {
                Toast.makeText(this@AddSupplierActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}