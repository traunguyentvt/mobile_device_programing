package com.tvt.dinnerdecider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import android.widget.Toast
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvFood: TextView
    private lateinit var etFood: EditText
//    private lateinit var btnAdd: Button

    var menus: MutableList<String> = mutableListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
//        btnAdd = findViewById(R.id.btnAdd)
//        btnAdd.setOnClickListener{
//            Toast.makeText(this, "VT T", Toast.LENGTH_SHORT).show()
//        }

        tvFood = findViewById(R.id.tvFood)
        etFood = findViewById(R.id.etFood)
    }

    fun onClickAddFood(view: View) {
        val newFood = etFood.text.toString().trim()
        if (newFood.isEmpty()) {
            Toast.makeText(this, "Please input a new food", Toast.LENGTH_SHORT).show()
            return
        }
        menus.add(newFood)
        etFood.text.clear()
        tvFood.text = newFood
    }

    fun onDecide(view: View) {
        val random = Random.nextInt(menus.size)
        tvFood.text = menus[random]
    }

}