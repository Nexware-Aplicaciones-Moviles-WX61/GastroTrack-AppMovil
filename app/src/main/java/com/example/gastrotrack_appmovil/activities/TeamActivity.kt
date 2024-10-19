package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.TaskAdapter
import com.example.gastrotrack_appmovil.models.Task
import com.example.gastrotrack_appmovil.network.TaskService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamActivity : AppCompatActivity(), TaskAdapter.OnItemClickListener {
    lateinit var tasks: ArrayList<Task>
    lateinit var taskAdapter: TaskAdapter
    private lateinit var rvTasks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team)

        val ibHomeReturnT = findViewById<ImageButton>(R.id.ibHomeReturnT)
        val ibAddMember = findViewById<ImageButton>(R.id.ibAddMember)
        val ibAddTask = findViewById<ImageButton>(R.id.ibAddTask)

        ibHomeReturnT.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        ibAddMember.setOnClickListener {
            val intent = Intent(this, AddMemberActivity::class.java)
            startActivity(intent)
        }

        ibAddTask.setOnClickListener {
            val intent = Intent(this, UpdateTaskActivity::class.java)
            startActivity(intent)
        }

        rvTasks = findViewById(R.id.rvTask)
        rvTasks.layoutManager = LinearLayoutManager(this)
        loadTasks { tasks ->
            taskAdapter = TaskAdapter(tasks, this)
            rvTasks.adapter = taskAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        loadTasks { tasks ->
            taskAdapter = TaskAdapter(tasks, this)
            rvTasks.adapter = taskAdapter
        }
    }

    override fun onItemClick(task: Task) {
        val intent = Intent(this, UpdateTaskActivity::class.java)
        startActivity(intent)
    }

    private fun loadTasks(onComplete: (List<Task>) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TaskService::class.java)
        val request = service.getAll()

        request.enqueue(object : retrofit2.Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                if (response.isSuccessful) {
                    val taskList: List<Task>? = response.body()
                    if (taskList != null) {
                        Log.d("TeamActivity", "Tasks loaded successfully")
                        onComplete(taskList)
                    }
                } else {
                    Log.e("TeamActivity", "Failed to load tasks: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.e("TeamActivity", "Error loading tasks", t)
            }
        })
    }
}