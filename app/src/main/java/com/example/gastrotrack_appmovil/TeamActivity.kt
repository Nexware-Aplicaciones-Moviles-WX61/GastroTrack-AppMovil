package com.example.gastrotrack_appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class TeamActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team)

        val ibHomeReturnT = findViewById<ImageButton>(R.id.ibHomeReturnT)

        ibHomeReturnT.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }

    }
}