package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Supplier

class SupplierAdapter(private val suppliers: List<Supplier>) :
    RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>() {

    inner class SupplierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val supplierName: TextView = itemView.findViewById(R.id.tvSupplierName)
        private val restaunrantName: TextView = itemView.findViewById(R.id.tvRestaurantName)
        private val phone: TextView = itemView.findViewById(R.id.tvPhoneSupplier)
        private val email: TextView = itemView.findViewById(R.id.tvEmailSupplier)
        private val supplierPhoto: ImageView = itemView.findViewById(R.id.ivSupplierPhoto)

        fun bind(supplier: Supplier) {
            supplierName.text = supplier.supplierName
            restaunrantName.text = supplier.restaunrantName
            phone.text = supplier.phone
            email.text = supplier.contactEmail


            Glide.with(itemView.context)
                .load(supplier.supplierPhoto)
                .into(supplierPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_provider, parent, false)
        return SupplierViewHolder(view)
    }

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        holder.bind(suppliers[position])
    }

    override fun getItemCount(): Int {
        return suppliers.size
    }
}