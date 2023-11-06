package com.tvt.walmart.models

import com.tvt.walmart.R
import java.io.Serializable

data class Category(var name: String, val image: Int): Serializable {
    companion object {
        fun createShopCategories(): ArrayList<Category> {
            return arrayListOf(
                Category(name = "Electronics", image = R.drawable.electronics),
                Category(name = "Clothing", image = R.drawable.clothing),
                Category(name = "Beauty", image = R.drawable.beauty),
                Category(name = "Food", image = R.drawable.food)
            )
        }
    }
}

