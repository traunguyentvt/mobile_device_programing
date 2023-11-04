package com.tvt.assignment_4.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tvt.assignment_4.R
import com.tvt.assignment_4.databinding.ActivityShoppingBinding
import com.tvt.assignment_4.models.User

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        val currentUser = intent.getSerializableExtra("currentUser", User::class.java)
        binding.tvWelcome.text = "${resources.getText(R.string.txt_welcome)} ${currentUser?.email}"
    }

    fun onClickRow1(view: View) {
        showToast(binding.tv1.text.toString().trim())
    }

    fun onClickRow2(view: View) {
        showToast(binding.tv2.text.toString().trim())
    }

    fun onClickRow3(view: View) {
        showToast(binding.tv3.text.toString().trim())
    }

    fun onClickRow4(view: View) {
        showToast(binding.tv4.text.toString().trim())
    }

    fun showToast(name: String) {
        Toast.makeText(this, "You have chosen the $name category of shopping", Toast.LENGTH_SHORT).show()
    }

}