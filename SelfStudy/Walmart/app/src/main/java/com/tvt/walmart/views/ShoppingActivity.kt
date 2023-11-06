package com.tvt.walmart.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.tvt.walmart.R
import com.tvt.walmart.databinding.ActivityShoppingBinding
import com.tvt.walmart.models.Category
import com.tvt.walmart.models.User
import com.tvt.walmart.viewmodels.CategoryAdapter
import com.tvt.walmart.views.listeners.CategoryListener

class ShoppingActivity : AppCompatActivity(), CategoryListener {

    private lateinit var binding: ActivityShoppingBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        val currentUser = intent.getSerializableExtra("currentUser", User::class.java)
        binding.tvWelcome.text = "${resources.getText(R.string.txt_welcome)} ${currentUser?.email}"

        if (!this::categoryAdapter.isInitialized) {
            categoryAdapter = CategoryAdapter(this, this)
        }

        binding.rvShop.adapter = categoryAdapter
        binding.rvShop.layoutManager = GridLayoutManager(this, 2)

        categoryAdapter.setData(Category.createShopCategories())
    }
    fun showToast(name: String) {
        Toast.makeText(this, "You have chosen the $name category of shopping", Toast.LENGTH_SHORT).show()
    }

    override fun selectedCategory(category: Category) {
        showToast(category.name)

        //update category
        if (category.name.contains("clicked")) {
            category.name = category.name.replace(" clicked", "")
        } else {
            category.name = "${category.name} clicked"
        }
        categoryAdapter.updateItem(category)

//        //remove this category
//        categoryAdapter.dataSet.remove(category)
//        categoryAdapter.reloadData()

//        //insert new category
//        categoryAdapter.dataSet.add(Category(name = "Electronics", image = R.drawable.electronics))
//        categoryAdapter.reloadData()
    }

}