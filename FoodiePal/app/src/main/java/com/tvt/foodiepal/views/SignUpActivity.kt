package com.tvt.foodiepal.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tvt.foodiepal.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        if (!this::sharedPreferences.isInitialized) {
            sharedPreferences = getSharedPreferences("user_login", Context.MODE_PRIVATE)
        }
    }

    fun onSignup(view: View) {
        val firstname = binding.etFirstName.text.toString().trim()
        if (firstname.isEmpty()) {
            showToast("First name is required")
            return
        }
        val lastname = binding.etLastName.text.toString().trim()
        if (lastname.isEmpty()) {
            showToast("Last name is required")
            return
        }
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            showToast("Email is required")
            return
        }
        val password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()) {
            showToast("Password is required")
            return
        }

        showToast("Your account is created successfully")

        var spEdit = sharedPreferences.edit()
        spEdit.putString("first_name", firstname)
        spEdit.putString("last_name", lastname)
        spEdit.putString("email", email)
        spEdit.putString("password", password)
        spEdit.apply()

        openLoginActivity()
    }

    private fun openLoginActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}