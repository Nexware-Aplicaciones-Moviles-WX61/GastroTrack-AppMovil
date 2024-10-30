package com.example.gastrotrack_appmovil.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.models.Task

class UpdateTaskActivity : AppCompatActivity() {
    private lateinit var etTaskName: EditText
    private lateinit var etTaskDescription: EditText
    private lateinit var etTaskDate: EditText
    private var taskId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_task)

        etTaskName = findViewById(R.id.etTitleTask)
        etTaskDescription = findViewById(R.id.etDescriptionTask)
        etTaskDate = findViewById(R.id.etDate)

        taskId = intent.getIntExtra("TASK_ID", 0)
        val taskName = intent.getStringExtra("TASK_NAME")
        val taskDescription = intent.getStringExtra("TASK_DESCRIPTION")
        val taskDate = intent.getStringExtra("TASK_DATE")

        etTaskName.setText(taskName)
        etTaskDescription.setText(taskDescription)
        etTaskDate.setText(taskDate)

        val btnCancelUT = findViewById<Button>(R.id.btnCanceledTask)
        btnCancelUT.setOnClickListener {
            finish()
        }

        val btnSave = findViewById<Button>(R.id.btnAcceptTask)
        btnSave.setOnClickListener {
            updateTask()
        }
    }

    private fun updateTask() {
        val database = AppDatabase.getDatabase(this)
        val taskDAO = database.taskDAO()

        val updatedTask = Task(
            taskid = taskId,
            taskName = etTaskName.text.toString(),
            taskDescription = etTaskDescription.text.toString(),
            taskDate = etTaskDate.text.toString()
        )


        taskDAO.updateTask(updatedTask)
        Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_SHORT).show()
        finish()
    }
}