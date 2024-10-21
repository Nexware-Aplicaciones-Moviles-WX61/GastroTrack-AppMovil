package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
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
    }

    private fun getLoggedInUser(): User {
        val sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE)
        val email = sharedPreferences.getString("user_email", null)

        return userDAO.getUserByEmail(email!!)
    }
}