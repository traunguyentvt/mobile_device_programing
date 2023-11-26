package com.tvt.foodiepal.viewModels

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.foodiepal.models.BlogModel

class BlogAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSet = ArrayList<BlogModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {

    }

    inner class BlogViewHolder(view: View): ViewHolder(view) {

        init {

        }

        fun setData(blog: BlogModel) {

        }

    }

}