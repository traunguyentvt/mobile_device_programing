package com.tvt.walmart.views.listeners

import com.tvt.walmart.models.Category

interface CategoryListener {
    fun selectedCategory(category: Category)
}