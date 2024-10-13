package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.User

class ProfileAdapter(private val userProfile: User) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {


    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstName: TextView = itemView.findViewById(R.id.tvFirstName)
        val tvLastName: TextView = itemView.findViewById(R.id.tvLastName)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val tvCompany: TextView = itemView.findViewById(R.id.tvCompany)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.tvFirstName.text = userProfile.firstName
        holder.tvLastName.text = userProfile.lastName
        holder.tvEmail.text = userProfile.email
        holder.tvCompany.text = userProfile.company
    }

    override fun getItemCount(): Int {
        return 1
    }
}