package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val productList: List<Product>,private val onEditClick: (Product) -> Unit, private val onDeleteClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tvProductName)
        val textViewDate: TextView = itemView.findViewById(R.id.tvDateManufacture)
        val textViewExpiration: TextView = itemView.findViewById(R.id.tvExperitation)
        val textViewStock: TextView = itemView.findViewById(R.id.tvStock)
        val textViewState: TextView = itemView.findViewById(R.id.tvState)
        val imageView: ImageView = itemView.findViewById(R.id.ivProductPhoto)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEditProduct)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteProduct)

        init {
            btnEdit.setOnClickListener {
                onEditClick(productList[adapterPosition])
            }

            btnDelete.setOnClickListener {
                onDeleteClick(productList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.textViewName.text = product.name
        holder.textViewDate.text = product.dateManufacture
        holder.textViewExpiration.text = product.dueDate
        holder.textViewStock.text = product.stock.toString()
        holder.textViewState.text = product.state

        Picasso.get()
            .load(product.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = productList.size
}


