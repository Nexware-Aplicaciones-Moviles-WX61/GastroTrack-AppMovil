package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Members

class MemberAdapter(private val membersList: List<Members>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMemberPhoto: ImageView = itemView.findViewById(R.id.ivMemberPhoto)
        val tvMemberName: TextView = itemView.findViewById(R.id.tvMemberName)
        val tvDescriptionMember: TextView = itemView.findViewById(R.id.tvDescriptionMember)
        val tvRoleMember: TextView = itemView.findViewById(R.id.tvRoleMember)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_members, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = membersList[position]
        holder.tvMemberName.text = member.name
        //holder.tvDescriptionMember.text = member.description
        //holder.tvRoleMember.text = member.role
    }

    override fun getItemCount(): Int {
        return membersList.size
    }
}
