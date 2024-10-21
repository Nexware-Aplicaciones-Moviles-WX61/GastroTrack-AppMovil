package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.NotificationAdapter
import com.example.gastrotrack_appmovil.communication.NotificationApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.NotificationDAO
import com.example.gastrotrack_appmovil.models.Notification
import com.example.gastrotrack_appmovil.network.NotificationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotificationActivity : AppCompatActivity() {

    private lateinit var rvNotifications: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notificationDAO: NotificationDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification)

        rvNotifications = findViewById(R.id.rvNotifications)

        val ibHomeReturnN = findViewById<ImageButton>(R.id.ibHomeReturnN)
        ibHomeReturnN.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val database = AppDatabase.getDatabase(this)
        notificationDAO = database.notificationDAO()

        rvNotifications.layoutManager = LinearLayoutManager(this)
        notificationAdapter = NotificationAdapter(mutableListOf(), ::onAcceptNotification, ::onDeleteNotification)
        rvNotifications.adapter = notificationAdapter


        loadNotifications()
    }

    private fun loadNotifications() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val notificationService = retrofit.create(NotificationService::class.java)
        val call = notificationService.getNotifications()

        call.enqueue(object : Callback<List<NotificationApiResponse>> {
            override fun onResponse(call: Call<List<NotificationApiResponse>>, response: Response<List<NotificationApiResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.let { notifications ->
                        val notificationList = notifications.map { it.toNotification() }
                        notificationAdapter.updateData(notificationList)


                    }
                } else {
                    Toast.makeText(this@NotificationActivity, "Error al cargar notificaciones: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<NotificationApiResponse>>, t: Throwable) {
                Toast.makeText(this@NotificationActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onAcceptNotification(notification: Notification) {
        Toast.makeText(this, "Notificación aceptada: ${notification.notificationName}", Toast.LENGTH_SHORT).show()
    }

    private fun onDeleteNotification(notification: Notification) {
        Toast.makeText(this, "Notificación eliminada", Toast.LENGTH_SHORT).show()
    }
}