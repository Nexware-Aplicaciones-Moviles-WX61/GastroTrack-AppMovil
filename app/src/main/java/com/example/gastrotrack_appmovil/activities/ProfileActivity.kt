package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.ProfileAdapter
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.UserDAO
import com.example.gastrotrack_appmovil.models.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileAdapter
    private lateinit var userDAO: UserDAO
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val ibHomeReturnP = findViewById<ImageButton>(R.id.ibHome)
        ibHomeReturnP.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rvProfile)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userDAO = AppDatabase.getDatabase(this).userDAO()
        currentUser = getLoggedInUser()


        adapter = ProfileAdapter(currentUser)
        recyclerView.adapter = adapter

        val ibEditUser = findViewById<Button>(R.id.btnUpdateUser)
        val ibDeleteUser = findViewById<Button>(R.id.btnDeleteUser)

        ibEditUser.setOnClickListener {
            editUser()
        }

        ibDeleteUser.setOnClickListener {
            deleteUser()
        }
    }

    private fun getLoggedInUser(): User {
        val sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE)
        val email = sharedPreferences.getString("user_email", null)

        return userDAO.getUserByEmail(email!!)
    }

    private fun editUser() {
        currentUser?.let { user ->
            val intent = Intent(this, UpdateUserActivity::class.java)
            intent.putExtra("USER_EMAIL", user.email)
            startActivity(intent)
        }
    }
    private fun deleteUser() {

        userDAO.deleteUser(currentUser)
        Toast.makeText(this, "Account deleted", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LogInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}