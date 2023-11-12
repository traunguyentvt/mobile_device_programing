package com.tvt.quizapp.views

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.tvt.quizapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isCorrectAnswer1 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            isCorrectAnswer1 = group.findViewById<RadioButton>(checkedId) == binding.radioAnswer3
        }
    }

    fun onSubmit(view: View) {
        var score = 0
        if (isCorrectAnswer1) {
            score += 50
        }
        if (binding.cbAnswer1.isChecked
            && binding.cbAnswer2.isChecked
            && binding.cbAnswer3.isChecked
            && binding.cbAnswer4.isChecked
        ) {
            score += 50
        }
        val formattedDate =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))
        val msg = "Congratulations! You submitted on $formattedDate, you achieved $score%"

        val builder = AlertDialog.Builder(this)
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
            reset()
        }
        builder.create()
            .show()
    }

    fun onReset(view: View) {
        reset()
    }

    private fun reset() {
        isCorrectAnswer1 = false

        binding.radioGroup1.clearCheck()

        binding.cbAnswer1.isChecked = false
        binding.cbAnswer2.isChecked = false
        binding.cbAnswer3.isChecked = false
        binding.cbAnswer4.isChecked = false
    }
}