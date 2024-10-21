package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Notification

class NotificationAdapter(
    private var notifications: List<Notification>,
    private val onAcceptClick: (Notification) -> Unit,
    private val onDeleteClick: (Notification) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvNotificationTitle)
        val description: TextView = view.findViewById(R.id.tvNotificationDescription)
        val acceptButton: Button = view.findViewById(R.id.btnAcceptNotification)
        val deleteButton: Button = view.findViewById(R.id.btnDeleteNotification)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.title.text = notification.notificationName
        holder.description.text = notification.notificationDescription

        holder.acceptButton.setOnClickListener {
            onAcceptClick(notification)
        }
        holder.deleteButton.setOnClickListener {
            onDeleteClick(notification)
        }
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun updateData(newNotifications: List<Notification>) {
        this.notifications = newNotifications
        notifyDataSetChanged()
    }
}