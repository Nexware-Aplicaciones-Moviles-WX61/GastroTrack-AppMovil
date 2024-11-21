package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.UserDAO

class LogInActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)

        etEmail = findViewById(R.id.et_usuario_login)
        etPassword = findViewById(R.id.et_password_login)
        btnLogin = findViewById(R.id.btLogin)

        val database = AppDatabase.getDatabase(this)
        userDAO = database.userDAO()

        btnLogin.setOnClickListener {
            loginUser()
        }

        val tvSignUp = findViewById<TextView>(R.id.tvSignAccount)
        tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email y contraseña son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val allUsers = userDAO.getAllUsers()

        val user = allUsers.find { it.email == email && it.password == password }

        if (user != null) {
            val sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("user_email", email)
            editor.apply()

            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}