package com.tvt.foodiepal.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvt.foodiepal.R
import com.tvt.foodiepal.listeners.RecipeListener
import com.tvt.foodiepal.models.RecipeModel

class RecipeAdapter(val listener: RecipeListener) : RecyclerView.Adapter<ViewHolder>() {
    private var dataSet = ArrayList<RecipeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)

        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is RecipeViewHolder -> holder.setData(dataSet.get(position), listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(list: ArrayList<RecipeModel>) {
        if (dataSet.size > 0) {
            dataSet.clear()
        }
        dataSet.addAll(list)
        reloadData()
    }

    fun addRecipe(recipe: RecipeModel) {
        dataSet.add(0, recipe)
        reloadData()
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    class RecipeViewHolder(view: View) : ViewHolder(view) {
        val imv: ImageView
        val tvName: TextView
        val tvIngredients: TextView
        val tvInstructions: TextView
        val tvRatings: TextView
        val tvDetail: TextView

        init {
            imv = view.findViewById(R.id.imv)
            tvName = view.findViewById(R.id.tvName)
            tvIngredients = view.findViewById(R.id.tvIngredients)
            tvInstructions = view.findViewById(R.id.tvInstructions)
            tvRatings = view.findViewById(R.id.tvRatings)
            tvDetail = view.findViewById(R.id.tvDetail)
        }

        fun setData(recipe: RecipeModel, listener: RecipeListener) {
            if (recipe.imgUri != null) {
                imv.setImageURI(recipe.imgUri)
            } else {
                if (recipe.image > 0) {
                    imv.setImageResource(recipe.image)
                } else {
                    imv.setImageResource(R.drawable.recipe_book)
                }
            }
            tvName.setText(recipe.name)
            tvIngredients.setText(recipe.ingredients)
            tvInstructions.setText(recipe.instructions)
            tvRatings.setText("${recipe.rating}-star")
            val url = recipe.url ?: ""
            if (!url.isEmpty()) {
                tvDetail.isGone = false
            } else {
                tvDetail.isGone = true
            }

            itemView.setOnClickListener {
                val url = recipe.url ?: ""
                if (!url.isEmpty()) {
                    listener.viewRecipe(recipe)
                }
            }
        }
    }
}