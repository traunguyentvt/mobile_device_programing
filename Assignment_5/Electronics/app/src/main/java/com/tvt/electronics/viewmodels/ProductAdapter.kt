package com.tvt.electronics.viewmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.electronics.R
import com.tvt.electronics.models.Product
import com.tvt.electronics.views.listeners.ProductListener
import java.text.DecimalFormat

class ProductAdapter(val listener: ProductListener)
    : RecyclerView.Adapter<ViewHolder>() {

    var dataSet = ArrayList<Product>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item, viewGroup, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.setData(dataSet.get(position), listener)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(list: ArrayList<Product>) {
        if (dataSet.size > 0) {
            dataSet.clear()
        }
        dataSet.addAll(list)
        reloadData()
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View) : ViewHolder(view) {
        val imv: ImageView
        val imvLogo: ImageView
        val tvName: TextView
        val tvPrice: TextView
        val tvDesc: TextView
        val btnAdd: Button

        val formatNumber = DecimalFormat("#,###.0")

        init {
            imv = view.findViewById(R.id.imv)
            imvLogo = view.findViewById(R.id.imvLogo)
            tvName = view.findViewById(R.id.tvName)
            tvPrice = view.findViewById(R.id.tvPrice)
            tvDesc = view.findViewById(R.id.tvDesc)
            btnAdd = view.findViewById(R.id.btnAdd)
        }

        fun setData(product: Product, listener: ProductListener) {
            imv.setImageResource(product.image)
            imvLogo.setImageResource(product.icon)
            tvName.setText(product.name)
            tvPrice.setText("$ ${formatNumber.format(product.price)}")
            tvDesc.setText(product.desc)

            itemView.setOnClickListener { itemView
                listener.viewProduct(product)
            }

            btnAdd.setOnClickListener { button ->
                listener.selectProduct(product)
            }
        }
    }
}