package com.example.gastrotrack_appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddMemberActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_member)

        val btnCancelAM=findViewById<Button>(R.id.btnCancelAM)

        btnCancelAM.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}