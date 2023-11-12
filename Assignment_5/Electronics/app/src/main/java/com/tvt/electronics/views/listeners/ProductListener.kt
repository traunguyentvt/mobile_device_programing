package com.tvt.electronics.views.listeners

import com.tvt.electronics.models.Product

interface ProductListener {
    fun selectProduct(product: Product)
    fun viewProduct(product: Product)
}