package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.communication.UserApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.UserDAO
import com.example.gastrotrack_appmovil.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etLastName: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var etCompany: EditText
    private lateinit var btnSignUp: Button

    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        etNombre = findViewById(R.id.etNombre)
        etLastName = findViewById(R.id.etLastName)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etEmail = findViewById(R.id.etEmail)
        etCompany = findViewById(R.id.etCompany)
        btnSignUp = findViewById(R.id.btnSignUp)

        val database = AppDatabase.getDatabase(this)
        userDAO = database.userDAO()

        btnSignUp.setOnClickListener {
            registerUser()
        }

        val tvSignAccount = findViewById<TextView>(R.id.tvStartAccount)
        tvSignAccount.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser() {
        val nombre = etNombre.text.toString().trim()
        val lastName = etLastName.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val company = etCompany.text.toString().trim()

        if (nombre.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || company.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val userApiResponse = UserApiResponse(
            firstName = nombre,
            lastName = lastName,
            email = email,
            password = password,
            company = company
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService = retrofit.create(UserService::class.java)
        val call = userService.createdUser(userApiResponse)

        call.enqueue(object : Callback<UserApiResponse> {
            override fun onResponse(call: Call<UserApiResponse>, response: Response<UserApiResponse>) {
                if (response.isSuccessful) {
                    val user = userApiResponse.toUser()
                    userDAO.insertUser(user)

                    Toast.makeText(this@SignUpActivity, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()

                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "Error al registrar usuario: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserApiResponse>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}