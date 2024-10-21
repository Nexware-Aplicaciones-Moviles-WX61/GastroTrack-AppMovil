package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Members
import com.example.gastrotrack_appmovil.models.Role

class MemberAdapter(private val members: List<Members>, private val roles: List<Role>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    class MemberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memberPhoto: ImageView = view.findViewById(R.id.ivMemberPhoto)
        val memberName: TextView = view.findViewById(R.id.tvMemberName)
        val memberDescription: TextView = view.findViewById(R.id.tvDescriptionMember)
        val memberRole: TextView = view.findViewById(R.id.tvRoleMember)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_members, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]

        holder.memberName.text = member.memberName
        holder.memberDescription.text = member.description


        Glide.with(holder.itemView.context)
            .load(member.photo)
            .into(holder.memberPhoto)

        val role = roles.find { it.id == member.roleId }
        holder.memberRole.text = role?.roleName ?: "Unknown Role"
    }

    override fun getItemCount(): Int {
        return members.size
    }
}