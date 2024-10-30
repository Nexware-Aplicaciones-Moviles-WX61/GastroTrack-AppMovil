package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Task
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(
    private val taskList: List<Task>,
    private val clickListener: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTaskName: TextView = view.findViewById(R.id.tvTaskName)
        val tvTaskDescription: TextView = view.findViewById(R.id.tvDescriptionTask)
        val tvTaskDate: TextView = view.findViewById(R.id.tvDateTask)
        val ibEditTask: ImageButton = view.findViewById(R.id.ibEditTask)

        fun bind(task: Task) {
            tvTaskName.text = task.taskName
            tvTaskDescription.text = task.taskDescription
            tvTaskDate.text = task.taskDate

            ibEditTask.setOnClickListener {
                clickListener(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)

        holder.itemView.setOnClickListener {
            clickListener(task)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}