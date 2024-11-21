package com.example.gastrotrack_appmovil.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.UserDAO
import com.example.gastrotrack_appmovil.models.User

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var userDAO: UserDAO
    private lateinit var currentUser: User
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etCompany: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_user)



        etFirstName = findViewById(R.id.etUdFirstName)
        etLastName = findViewById(R.id.etUdLastName)
        etEmail = findViewById(R.id.etUdEmail)
        etCompany = findViewById(R.id.etUdCompany)
        etPassword = findViewById(R.id.etUdPassword)

        val userEmail = intent.getStringExtra("USER_EMAIL")
        userDAO = AppDatabase.getDatabase(this).userDAO()

        if (userEmail.isNullOrEmpty()) {
            Toast.makeText(this, "Error al cargar el usuario", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        currentUser = userDAO.getUserByEmail(userEmail) ?: run {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        populateUserData()

        val btnAccept = findViewById<Button>(R.id.btnAcceptUser)
        btnAccept.setOnClickListener {
            updateUser()
        }

        val btnCanceled = findViewById<Button>(R.id.btnCanceledUser)
        btnCanceled.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun populateUserData() {
        etFirstName.setText(currentUser.firstName)
        etLastName.setText(currentUser.lastName)
        etEmail.setText(currentUser.email)
        etCompany.setText(currentUser.company)
        etPassword.setText(currentUser.password)
    }

    private fun updateUser() {
        val updatedUser = currentUser.copy(
            firstName = etFirstName.text.toString(),
            lastName = etLastName.text.toString(),
            email = etEmail.text.toString(),
            company = etCompany.text.toString(),
            password = etPassword.text.toString()
        )
        userDAO.updateUser(updatedUser)
        Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
        finish()
    }
}
