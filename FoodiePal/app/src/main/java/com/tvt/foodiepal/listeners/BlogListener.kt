package com.tvt.foodiepal.listeners

import com.tvt.foodiepal.models.BlogModel

interface BlogListener {
    fun viewBlog(blog: BlogModel)
}