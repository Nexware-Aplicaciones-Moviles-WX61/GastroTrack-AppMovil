package com.example.gastrotrack_appmovil.activities


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.communication.TaskApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.db.TaskDAO
import com.example.gastrotrack_appmovil.network.TaskService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddTaskActivity : AppCompatActivity() {

    private lateinit var etTaskName: EditText
    private lateinit var etTaskDescription: EditText
    private lateinit var etTaskDate: EditText
    private lateinit var btnAddTask: Button
    private lateinit var taskDAO: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_task)

        etTaskName = findViewById(R.id.etTitleTask)
        etTaskDescription = findViewById(R.id.etDescriptionTask)
        etTaskDate = findViewById(R.id.etDate)
        btnAddTask = findViewById(R.id.btnAcceptTask)

        val database = AppDatabase.getDatabase(this)
        taskDAO=database.taskDAO()
        // Configurar el botón para agregar tarea
        btnAddTask.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        Log.d("AddTaskActivity", "addTask() called")
        val taskName = etTaskName.text.toString().trim()
        val taskDescription = etTaskDescription.text.toString().trim()
        val taskDate = etTaskDate.text.toString().trim()

        if (taskName.isEmpty() || taskDescription.isEmpty() || taskDate.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val taskApiResponse = TaskApiResponse(
            taskName = taskName,
            taskDescription = taskDescription,
            taskDate = taskDate
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val taskService = retrofit.create(TaskService::class.java)
        val call = taskService.createTask(taskApiResponse)

        call.enqueue(object : Callback<TaskApiResponse> {
            override fun onResponse(call: Call<TaskApiResponse>, response: Response<TaskApiResponse>) {
                if (response.isSuccessful) {
                    val newTask = taskApiResponse.toTask()

                    taskDAO.insertTask(newTask)

                    Toast.makeText(this@AddTaskActivity, "Tarea añadida exitosamente", Toast.LENGTH_SHORT).show()

                    finish()
                } else {
                    Toast.makeText(this@AddTaskActivity, "Error al añadir tarea: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TaskApiResponse>, t: Throwable) {
                Toast.makeText(this@AddTaskActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}