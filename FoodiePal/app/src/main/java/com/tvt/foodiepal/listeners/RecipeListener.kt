package com.tvt.foodiepal.listeners

import com.tvt.foodiepal.models.RecipeModel

interface RecipeListener {
    fun viewRecipe(recipe: RecipeModel)
}