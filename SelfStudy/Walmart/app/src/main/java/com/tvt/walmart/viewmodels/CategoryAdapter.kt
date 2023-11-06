package com.tvt.walmart.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.walmart.R
import com.tvt.walmart.models.Category
import com.tvt.walmart.views.listeners.CategoryListener

class CategoryAdapter(val context: Context, val listener: CategoryListener) : RecyclerView.Adapter<ViewHolder>() {

    var dataSet: ArrayList<Category> = ArrayList()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_item, viewGroup, false)

        return CategoryViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

//        viewHolder.tvName.text = dataSet.get(position).name
//        viewHolder.imv.setImageResource(dataSet.get(position).image)
//        viewHolder.tvName.setOnClickListener { view

        when(viewHolder) {
            is CategoryViewHolder -> viewHolder.setData(dataSet.get(position), listener)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(categories: ArrayList<Category>){
        dataSet.clear()
        dataSet.addAll(categories)
        notifyDataSetChanged()
    }

    fun updateItem(category: Category){
        val existedIndex = dataSet.indexOf(category)
        if (existedIndex < 0) {
            return
        }
        dataSet.set(existedIndex, category)
        notifyItemChanged(existedIndex)
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class CategoryViewHolder(view: View) : ViewHolder(view) {
        val tvName: TextView
        val imv: ImageView

        init {
            // Define click listener for the ViewHolder's View
            tvName = view.findViewById(R.id.tvName)
            imv = view.findViewById(R.id.imv)
        }

        fun setData(data: Category, listener: CategoryListener) {
            tvName.setText(data.name)
            imv.setImageResource(data.image)

            tvName.setOnClickListener { nameView ->
                listener.selectedCategory(data)
            }
            imv.setOnClickListener { imvView ->
                listener.selectedCategory(data)
            }
        }
    }

}