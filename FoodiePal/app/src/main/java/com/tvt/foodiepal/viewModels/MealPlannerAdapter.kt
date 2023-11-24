package com.tvt.foodiepal.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.foodiepal.R
import com.tvt.foodiepal.models.MealPlannerModel
import java.text.SimpleDateFormat

class MealPlannerAdapter: RecyclerView.Adapter<ViewHolder>() {
    private var dataSet = ArrayList<MealPlannerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_planner, parent, false)

        return MealPlannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is MealPlannerViewHolder -> holder.setData(dataSet.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(list: ArrayList<MealPlannerModel>) {
        if (dataSet.size > 0) {
            dataSet.clear()
        }
        dataSet.addAll(list)
        reloadData()
    }

    fun addMealPlanner(mealPlanner: MealPlannerModel) {
        dataSet.add(0, mealPlanner)
        reloadData()
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    class MealPlannerViewHolder(view: View): ViewHolder(view) {

        private val tvName: TextView
        private val tvMeals: TextView

        private val dateFormatter = SimpleDateFormat("EEEE, MM/dd/yyyy")

        init {
            tvName = view.findViewById(R.id.tvName)
            tvMeals = view.findViewById(R.id.tvMeal)
        }

        fun setData(mealPlanner: MealPlannerModel) {
            tvName.text = dateFormatter.format(mealPlanner.date)
            var meals: StringBuilder = StringBuilder()
            if (mealPlanner.meals != null) {
                mealPlanner.meals.forEach { item ->
                    meals.append(item.name)
                    meals.append(": ")
                    meals.append(item.meal)
                    meals.append("\n\n")
                }
            }
            var msg = meals.toString()
            if (msg.length > 2) {
                msg = msg.substring(0, msg.length-2)
            }
            tvMeals.text = msg
        }
    }
}