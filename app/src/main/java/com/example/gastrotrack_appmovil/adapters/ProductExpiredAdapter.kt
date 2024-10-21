package com.example.gastrotrack_appmovil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Product

class ProductExpiredAdapter(private val context: Context, private val productList: List<Product>) : RecyclerView.Adapter<ProductExpiredAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivExpiredProductPhoto)
        val productName: TextView = itemView.findViewById(R.id.tvExpiredProductName)
        val expirationDate: TextView = itemView.findViewById(R.id.tvExpiredProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.prototype_product_expired, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        Glide.with(context)
            .load(product.image)
            .into(holder.imageView)

        holder.productName.text = product.name
        holder.expirationDate.text = product.dueDate
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}


