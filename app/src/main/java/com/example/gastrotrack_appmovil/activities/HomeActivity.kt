package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.MainActivity
import com.example.gastrotrack_appmovil.R

class HomeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val ibInventory = findViewById<ImageButton>(R.id.ibInventory)
        val ibNotificaction = findViewById<ImageButton>(R.id.ibNotificaction)
        val ibTeam = findViewById<ImageButton>(R.id.ibTeam)
        val ibProfile = findViewById<ImageView>(R.id.ivProfile)
        val ibExit = findViewById<ImageButton>(R.id.ibExit)

        ibInventory.setOnClickListener {
            val intent =
                Intent(this, InventoryActivity::class.java)
            startActivity(intent)
        }

        ibNotificaction.setOnClickListener {
            val intent =
                Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        ibTeam.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        ibProfile.setOnClickListener {
            val intent =
                Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        ibExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}