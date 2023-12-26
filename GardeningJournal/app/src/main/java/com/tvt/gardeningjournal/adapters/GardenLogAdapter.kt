package com.tvt.gardeningjournal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.gardeningjournal.R
import com.tvt.gardeningjournal.listeners.GardenLogListener
import com.tvt.gardeningjournal.models.Plant

class GardenLogAdapter(val listener: GardenLogListener): RecyclerView.Adapter<ViewHolder>() {
    private var dataSet = ArrayList<Plant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_garden_log, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is PlantViewHolder -> holder.setData(dataSet.get(position), listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(list: List<Plant>) {
        if (dataSet.size > 0) {
            dataSet.clear()
        }
        dataSet.addAll(list)
    }

//    fun addSampleData(list: Array<Plant>) {
//        if (dataSet.size > 0) {
//            dataSet.clear()
//        }
//        dataSet.addAll(list)
//    }

    fun addPlant(plant: Plant) {
        dataSet.add(0, plant)
        reloadData()
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    class PlantViewHolder(view: View): ViewHolder(view) {
        val tvName: TextView

        init {
            tvName = view.findViewById(R.id.tvName)
        }

        fun setData(plant: Plant, listener: GardenLogListener) {
            tvName.text = plant.name

            itemView.setOnClickListener {
                listener.viewGardenLog(plant)
            }
        }
    }
}