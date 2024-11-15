package com.example.gastrotrack_appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastrotrack_appmovil.activities.LogInActivity
import com.example.gastrotrack_appmovil.activities.SignUpActivity
import com.example.gastrotrack_appmovil.activities.TermsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginButton = findViewById<Button>(R.id.btLog)
        val signUpButton = findViewById<Button>(R.id.btSign)

        loginButton.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            showTermsAndConditionsDialog()
        }
    }

    private fun showTermsAndConditionsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Términos y Condiciones")
        builder.setMessage("Por favor, lee y acepta los Términos y Condiciones antes de registrarte.")

        builder.setPositiveButton("Ver Términos") { dialog, _ ->
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNeutralButton("Aceptar") { dialog, _ ->
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        builder.show()
    }
}
