package com.example.gastrotrack_appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class  UpdateTaskActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_task)

        val btnCancelUT = findViewById<Button>(R.id.btnCanceledUT)

        btnCancelUT.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}