package com.tvt.assignment_4.views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tvt.assignment_4.databinding.ActivityMainBinding
import com.tvt.assignment_4.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var users: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        if (!this::users.isInitialized) {
            users = ArrayList<User>()
        }
        users.add(User("John", "Tan", "tvt@gmail.com", "123"))
        users.add(User("Vinh", "Vinh", "vinhvinh@gmail.com", "1234"))
        users.add(User("VT", "Tan", "vtt@gmail.com", "12345"))
        users.add(User("TT", "iOS", "ttn@gmail.com", "123456"))
        users.add(User("Kid", "Nguyen", "nvnt@gmail.com", "1234567"))

        //update new user from signup activity
        val newUser = intent.getSerializableExtra("newUser", User::class.java)
        if (newUser != null) {
            users.add(newUser)
        }
    }

    fun onSignin(view: View) {
        val email = binding.etEmailAddress.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isEmpty() || password.isEmpty()) {
            showToast("Email and Password are required")
            return
        }
        users.forEach { user ->
            if (user.email.equals(email) && user.password.equals(password)) {
                gotoShoppingActivity(user)
                return
            }
        }
        showToast("Cannot find any user with your email and password")
    }

    fun onSignup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    fun onOpenEmail(view: View) {
        val email = binding.etEmailAddress.text.toString().trim()
        lateinit var password: String
        var isExist = false
       for (user in users) {
            if (user.email.equals(email)) {
                isExist = true
                password = user.password
                break
            }
        }

        if (!isExist) {
            showToast("You don't have any account with this email $email")
            return
        }
        val messBody = "Your password is $password"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailTo:")
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Walmart: Forgot Password")
        intent.putExtra(Intent.EXTRA_TEXT, messBody)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun gotoShoppingActivity(user: User) {
        val intent = Intent(this, ShoppingActivity::class.java)
        intent.putExtra("currentUser", user)
        startActivity(intent)
    }
}