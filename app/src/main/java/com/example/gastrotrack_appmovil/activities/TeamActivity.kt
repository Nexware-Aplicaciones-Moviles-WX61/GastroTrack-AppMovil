package com.example.gastrotrack_appmovil.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.adapters.MemberAdapter
import com.example.gastrotrack_appmovil.adapters.TaskAdapter
import com.example.gastrotrack_appmovil.communication.RoleApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.models.Members
import com.example.gastrotrack_appmovil.models.Role
import com.example.gastrotrack_appmovil.models.Task
import com.example.gastrotrack_appmovil.network.RoleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamActivity : AppCompatActivity() {
    private lateinit var recyclerViewMembers: RecyclerView
    private lateinit var memberAdapter: MemberAdapter
    private var memberList: MutableList<Members> = mutableListOf()
    private var roleList: MutableList<Role> = mutableListOf()

    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private var taskList: MutableList<Task> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team)

        val ibHomeReturnT = findViewById<ImageButton>(R.id.ibHomeReturnT)
        val ibAddMember = findViewById<ImageButton>(R.id.ibAddMember)
        val ibAddTask = findViewById<ImageButton>(R.id.ibAddTask)

        recyclerViewMembers = findViewById(R.id.rvMembers)
        recyclerViewMembers.layoutManager = LinearLayoutManager(this)

        recyclerViewTasks = findViewById(R.id.rvTask)
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        taskAdapter = TaskAdapter(taskList) { task ->
            val intent = Intent(this, UpdateTaskActivity::class.java).apply {
                putExtra("TASK_ID", task.taskid)
                putExtra("TASK_NAME", task.taskName)
                putExtra("TASK_DESCRIPTION", task.taskDescription)
                putExtra("TASK_DATE", task.taskDate)
            }
            startActivity(intent)
        }
        recyclerViewTasks.adapter = taskAdapter

        loadMembers()
        loadRoles()
        loadTasks()

        ibHomeReturnT.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        ibAddMember.setOnClickListener {
            val intent = Intent(this, AddMemberActivity::class.java)
            startActivity(intent)
        }

        ibAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadMembers() {
        val database = AppDatabase.getDatabase(this)
        val memberDAO = database.membersDAO()

        memberList.clear()
        val members = memberDAO.getAllMembers()
        memberList.addAll(members)

        memberAdapter = MemberAdapter(memberList, roleList)
        recyclerViewMembers.adapter = memberAdapter
    }

    private fun loadRoles() {
        val database = AppDatabase.getDatabase(this)
        val roleDAO = database.roleDAO()

        roleList.clear()
        val roles = roleDAO.getAllRoles()
        roleList.addAll(roles)

        if (roleList.isEmpty()) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://gastrotrack-backend.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val roleService = retrofit.create(RoleService::class.java)
            roleService.getRoles().enqueue(object : Callback<List<RoleApiResponse>> {
                override fun onResponse(call: Call<List<RoleApiResponse>>, response: Response<List<RoleApiResponse>>) {
                    if (response.isSuccessful) {
                        val rolesApiResponse = response.body() ?: emptyList()
                        roleList.addAll(rolesApiResponse.map { it.toRole() })
                        roleDAO.getAllRoles()

                    } else {
                        Log.e("TeamActivity", "Error al cargar roles: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<RoleApiResponse>>, t: Throwable) {
                    Log.e("TeamActivity", "Error de red al cargar roles: ${t.message}")
                }
            })
        }
    }

    private fun loadTasks() {
        val database = AppDatabase.getDatabase(this)
        val taskDAO = database.taskDAO()

        taskList.clear()
        val tasks = taskDAO.getAllTasks()
        taskList.addAll(tasks)
        Log.d("TeamActivity", "Loaded tasks: ${taskList.size}")
    }
}