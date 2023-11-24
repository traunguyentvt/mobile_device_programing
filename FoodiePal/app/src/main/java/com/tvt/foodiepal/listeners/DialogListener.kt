package com.tvt.foodiepal.listeners

import com.tvt.foodiepal.models.BaseModel
import com.tvt.foodiepal.models.MealPlannerModel
import com.tvt.foodiepal.models.RecipeModel

interface DialogListener {
    fun addData(model: BaseModel) {}
    fun addRecipe(recipe: RecipeModel) {}
    fun addMealPlanner(mealPlanner: MealPlannerModel) {}
}