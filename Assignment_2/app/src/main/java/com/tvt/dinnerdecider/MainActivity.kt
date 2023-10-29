package com.tvt.dinnerdecider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.random.Random
import android.widget.Toast
import com.tvt.dinnerdecider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //old approach: declare views and connect them using findViewById
//    private lateinit var tvFood: TextView
//    private lateinit var etFood: EditText
//    private lateinit var btnAdd: Button

    //new approach: using Binding to connect views
    private lateinit var binding: ActivityMainBinding

    var menus: MutableList<String> = mutableListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    fun initView() {
        //new way to bind views from XML layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //old one to outlet views from XML layout
//        setContentView(R.layout.activity_main)
//        btnAdd = findViewById(R.id.btnAdd)
//        btnAdd.setOnClickListener{
//            Toast.makeText(this, "VT T", Toast.LENGTH_SHORT).show()
//        }
//        tvFood = findViewById(R.id.tvFood)
//        etFood = findViewById(R.id.etFood)
    }

    fun onClickAddFood(view: View) {
        val newFood = binding.etFood.text.toString().trim()
        if (newFood.isEmpty()) {
            Toast.makeText(this, "Please input a new food", Toast.LENGTH_SHORT).show()
            return
        }
        menus.add(newFood)
        binding.etFood.text.clear()
        binding.tvFood.text = newFood
    }

    fun onDecide(view: View) {
        val random = Random.nextInt(menus.size)
        binding.tvFood.text = menus[random]
    }

}