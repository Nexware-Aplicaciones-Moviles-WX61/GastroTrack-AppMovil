package com.example.gastrotrack_appmovil.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.communication.ReportApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.ReportDAO
import com.example.gastrotrack_appmovil.network.ReportService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddReportActivity : AppCompatActivity() {

    private lateinit var etReportName: EditText
    private lateinit var etReportDescription: EditText
    private lateinit var etReportDate: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnAccept: Button

    private lateinit var reportDAO: ReportDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_report)

        etReportName = findViewById(R.id.editTextText2)
        etReportDescription = findViewById(R.id.editTextText3)
        etReportDate = findViewById(R.id.editTextText4)
        btnCancel = findViewById(R.id.button8)
        btnAccept = findViewById(R.id.button9)

        val database = AppDatabase.getDatabase(this)
        reportDAO = database.reportDAO()

        btnCancel.setOnClickListener { finish() }

        btnAccept.setOnClickListener { registerReport() }
    }

    private fun registerReport() {
        val reportName = etReportName.text.toString().trim()
        val reportDescription = etReportDescription.text.toString().trim()
        val reportDate = etReportDate.text.toString().trim()

        if (reportName.isEmpty() || reportDescription.isEmpty() || reportDate.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val reportApiResponse = ReportApiResponse(
            reportName = reportName,
            reportDescription = reportDescription,
            reportDate = reportDate
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val reportService = retrofit.create(ReportService::class.java)
        val call = reportService.createReport(reportApiResponse)

        call.enqueue(object : Callback<ReportApiResponse> {
            override fun onResponse(call: Call<ReportApiResponse>, response: Response<ReportApiResponse>) {
                if (response.isSuccessful) {
                    val report = reportApiResponse.toReport()
                    Thread {
                        reportDAO.insertReport(report)
                    }.start()

                    Toast.makeText(this@AddReportActivity, "Reporte registrado exitosamente", Toast.LENGTH_SHORT).show()

                    finish()
                } else {
                    Toast.makeText(this@AddReportActivity, "Error al registrar reporte: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReportApiResponse>, t: Throwable) {
                Toast.makeText(this@AddReportActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}