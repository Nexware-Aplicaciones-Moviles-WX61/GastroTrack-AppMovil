package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Task

class TaskAdapter (private val news: List<Task>, private val clickListener: OnItemClickListener) : Adapter<TaskAdapter.TaskViewHolder>(){
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTaskName: TextView = itemView.findViewById(R.id.tvTaskName)
        private val tvTaskDescription: TextView = itemView.findViewById(R.id.tvDescriptionTask)
        private val tvTaskDate: TextView = itemView.findViewById(R.id.tvDateTask)
        private val ibEditButton: ImageButton = itemView.findViewById(R.id.ibEditTask)

        fun bind(task: Task, clickListener: OnItemClickListener){
            tvTaskName.text = task.name.toString()
            tvTaskDescription.text = task.description.toString()
            tvTaskDate.text = task.date.toString()
            ibEditButton.setOnClickListener {
                clickListener.onItemClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(news[position], clickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
    }
}