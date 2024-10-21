package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.MainActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.ProductExpiredAdapter
import com.example.gastrotrack_appmovil.adapters.ReportAdapter
import com.example.gastrotrack_appmovil.adapters.SupplierAdapter
import com.example.gastrotrack_appmovil.db.AppDatabase

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerViewSuppliers: RecyclerView
    private lateinit var recyclerViewReports: RecyclerView
    private lateinit var recyclerViewExpiredProducts: RecyclerView

    private lateinit var supplierAdapter: SupplierAdapter
    private lateinit var reportAdapter: ReportAdapter
    private lateinit var productExpiredAdapter: ProductExpiredAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Configurar los botones de navegación
        val ibInventory = findViewById<ImageButton>(R.id.ibInventory)
        ibInventory.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }

        val ibNotificaction = findViewById<ImageButton>(R.id.ibNotificaction)
        ibNotificaction.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        val ibTeam = findViewById<ImageButton>(R.id.ibTeam)
        ibTeam.setOnClickListener {
            startActivity(Intent(this, TeamActivity::class.java))
        }

        val ibProfile = findViewById<ImageView>(R.id.ivProfile)
        ibProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val ibExit = findViewById<ImageButton>(R.id.ibExit)
        ibExit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val ibAddProvider = findViewById<ImageButton>(R.id.ibAddProvider)
        ibAddProvider.setOnClickListener {
            startActivity(Intent(this, AddSupplierActivity::class.java))
        }

        val ibAddReport = findViewById<ImageButton>(R.id.ibAddReport)
        ibAddReport.setOnClickListener {
            startActivity(Intent(this, AddReportActivity::class.java))
        }

        recyclerViewSuppliers = findViewById(R.id.rvProviders)
        recyclerViewReports = findViewById(R.id.rvReports)
        recyclerViewExpiredProducts = findViewById(R.id.rvProductsExpire)

        recyclerViewSuppliers.layoutManager = LinearLayoutManager(this)
        recyclerViewReports.layoutManager = LinearLayoutManager(this)
        recyclerViewExpiredProducts.layoutManager = LinearLayoutManager(this)

        loadSuppliers()
        loadReports()
        loadExpiredProducts()
    }

    private fun loadSuppliers() {
        val dao = AppDatabase.getDatabase(this).supplierDAO()
        val suppliers = dao.getAllSuppliers()

        if (suppliers.isNotEmpty()) {
            supplierAdapter = SupplierAdapter(suppliers.toMutableList())
            recyclerViewSuppliers.adapter = supplierAdapter
        } else {
            Toast.makeText(this, "No hay proveedores disponibles.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadReports() {
        val dao = AppDatabase.getDatabase(this).reportDAO()
        val reports = dao.getAllReports()

        if (reports.isNotEmpty()) {
            reportAdapter = ReportAdapter(reports.toMutableList()) { report ->

            }
            recyclerViewReports.adapter = reportAdapter
        } else {
            Toast.makeText(this, "No hay informes disponibles.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadExpiredProducts() {
        val dao = AppDatabase.getDatabase(this).productDAO()
        val expiredProducts = dao.getAllProducts()

        if (expiredProducts.isNotEmpty()) {
            productExpiredAdapter = ProductExpiredAdapter(this, expiredProducts.toMutableList())
            recyclerViewExpiredProducts.adapter = productExpiredAdapter
        } else {
            Toast.makeText(this, "No hay productos expirados.", Toast.LENGTH_SHORT).show()
        }
    }
}