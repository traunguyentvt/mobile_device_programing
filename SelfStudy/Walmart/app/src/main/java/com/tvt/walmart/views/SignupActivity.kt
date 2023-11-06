package com.tvt.walmart.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tvt.walmart.databinding.ActivitySignupBinding
import com.tvt.walmart.models.User
import com.tvt.walmart.views.MainActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        val newUser = User(firstname, lastname, email, password)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("newUser", newUser)
        startActivity(intent)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}