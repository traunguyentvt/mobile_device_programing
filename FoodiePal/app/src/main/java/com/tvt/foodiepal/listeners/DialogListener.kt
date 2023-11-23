package com.tvt.foodiepal.listeners

import com.tvt.foodiepal.models.BaseModel
import com.tvt.foodiepal.models.RecipeModel

interface DialogListener {
    fun addData(model: BaseModel) {}
    fun addRecipe(recipe: RecipeModel) {}
}