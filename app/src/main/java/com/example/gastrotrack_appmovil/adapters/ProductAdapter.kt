package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<Product>, val clickLister: OnItemClickListener) : Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View): ViewHolder(itemView) {
        private val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        private val tvDateManufacture = itemView.findViewById<TextView>(R.id.tvDateManufacture)
        private val tvExperitation = itemView.findViewById<TextView>(R.id.tvExperitation)
        private val tvStock = itemView.findViewById<TextView>(R.id.tvStock)
        private val tvState = itemView.findViewById<TextView>(R.id.tvState)
        private val ivProductPhoto = itemView.findViewById<ImageView>(R.id.ivProductPhoto)

        private val btDeleteProduct = itemView.findViewById<ImageButton>(R.id.btnDeleteProduct)
        private val btnEditProduct = itemView.findViewById<ImageButton>(R.id.btnEditProduct)

        fun bind(products: Product, clicListener: OnItemClickListener){
            tvProductName.text = products.name
            tvDateManufacture.text = products.dateManufacture
            tvExperitation.text = products.dueDate
            tvStock.text = products.stock.toString()
            tvState.text = products.state
            Picasso.get()
                .load(products.image)
                .into(ivProductPhoto)

            btDeleteProduct.setOnClickListener {
                clickLister.onDeleteClick(products)
            }

            btnEditProduct.setOnClickListener {
                clickLister.onEditClick(products)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], clickLister)
    }

    interface OnItemClickListener {
        fun onDeleteClick(products: Product)
        fun onEditClick(products: Product)
    }
}


