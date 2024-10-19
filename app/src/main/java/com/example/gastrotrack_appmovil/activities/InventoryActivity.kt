package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        val ibHomeReturnI = findViewById<ImageButton>(R.id.ibHomeReturnI)

        ibHomeReturnI.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }

        /*val ibUpdate = findViewById<ImageButton>(R.id.ibUpdate)
        ibUpdate.setOnClickListener {
            val intent = Intent(this, UpdateProductActivity::class.java)
            startActivity(intent)
        }*/

        val ibAdd=findViewById<ImageButton>(R.id.ibAdd)
        ibAdd.setOnClickListener{
            val intent = Intent(this, UpdateProductActivity::class.java)
            startActivity(intent)
        }

    }
}
