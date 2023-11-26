package com.tvt.foodiepal.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.foodiepal.R
import com.tvt.foodiepal.listeners.BlogListener
import com.tvt.foodiepal.models.BlogModel

class BlogAdapter(val listener: BlogListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSet = ArrayList<BlogModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is BlogViewHolder -> holder.setData(dataSet.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(list: ArrayList<BlogModel>) {
        if (dataSet.size > 0) {
            dataSet.clear()
        }
        dataSet.addAll(list)
    }

    fun addBlog(blog: BlogModel) {
        dataSet.add(0, blog)
        reloadData()
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    inner class BlogViewHolder(view: View): ViewHolder(view) {

        val tvName: TextView
        val tvDesc: TextView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvDesc = view.findViewById(R.id.tvDesc)
        }

        fun setData(blog: BlogModel) {
            tvName.text = blog.name
            tvDesc.text = blog.desc ?: ""

            itemView.setOnClickListener {
                val url = blog.url ?: ""
                if (!url.isEmpty()) {
                    listener.viewBlog(blog)
                }
            }
        }
    }

}