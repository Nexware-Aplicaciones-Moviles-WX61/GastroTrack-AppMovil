package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Product
import com.squareup.picasso.Picasso

class ProductExpiredAdapter(private val products: List<Product>) :
    Adapter<ProductExpiredAdapter.ProductExpiredViewHolder>() {

    inner class ProductExpiredViewHolder(itemView: View): ViewHolder(itemView) {
        private val tvExpiredProductName = itemView.findViewById<TextView>(R.id.tvExpiredProductName)
        private val tvExpiredProduct = itemView.findViewById<TextView>(R.id.tvExpiredProduct)
        private val ivExpiredProductPhoto = itemView.findViewById<ImageView>(R.id.ivExpiredProductPhoto)

        fun bind(products: Product){
            tvExpiredProductName.text = products.name
            tvExpiredProduct.text = products.dueDate
            Picasso.get()
                .load(products.image)
                .into(ivExpiredProductPhoto)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductExpiredViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_product_expired, parent, false)
        return ProductExpiredViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductExpiredViewHolder, position: Int) {
        holder.bind(products[position])
    }



}


