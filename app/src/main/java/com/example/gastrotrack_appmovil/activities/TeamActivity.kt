package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R

class TeamActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team)

        val ibHomeReturnT = findViewById<ImageButton>(R.id.ibHomeReturnT)
        val ibAddMember=findViewById<ImageButton>(R.id.ibAddMember)
        val ibAddTask=findViewById<ImageButton>(R.id.ibAddTask)
        val ibUpdateTask=findViewById<ImageButton>(R.id.ibUpdateTask)

        ibHomeReturnT.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }

        ibAddMember.setOnClickListener{
            val intent=Intent(this, AddMemberActivity::class.java)
            startActivity(intent)
        }

        ibAddTask.setOnClickListener{
            val intent=Intent(this, UpdateTaskActivity::class.java)
            startActivity(intent)
        }

        ibUpdateTask.setOnClickListener{
            val intent=Intent(this, UpdateTaskActivity::class.java)
            startActivity(intent)
        }

    }
}