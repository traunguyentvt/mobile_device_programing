package com.tvt.foodiepal.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tvt.foodiepal.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        if (!this::sharedPreferences.isInitialized) {
            sharedPreferences = getSharedPreferences("user_login", Context.MODE_PRIVATE)
        }
        val email = sharedPreferences.getString("email", "")
        var password = sharedPreferences.getString("password", "")
        binding.etEmailAddress.setText(email)
        binding.etPassword.setText(password)
    }

    fun onSignin(view: View) {
        val email = binding.etEmailAddress.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isEmpty() || password.isEmpty()) {
            showToast("Email and Password are required")
            return
        }

        val savedEmail = sharedPreferences.getString("email", "")
        var savedPassword = sharedPreferences.getString("password", "")

        if (email.equals(savedEmail) && password.equals(savedPassword)) {
            gotoShoppingActivity()
            return
        }

        showToast("Cannot find any user with your email and password")
    }

    fun onSignup(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    fun onOpenEmail(view: View) {
        val email = binding.etEmailAddress.text.toString().trim()
        if (email.isEmpty()) {
            showToast("Please input your email")
            return
        }
        val savedEmail = sharedPreferences.getString("email", "")
        if (!email.equals(savedEmail)) {
            showToast("You don't have any account with this email $email")
            return
        }
        var savedPassword = sharedPreferences.getString("password", "")
        val messBody = "Your password is $savedPassword"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Walmart: Forgot Password")
        intent.putExtra(Intent.EXTRA_TEXT, messBody)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun gotoShoppingActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}