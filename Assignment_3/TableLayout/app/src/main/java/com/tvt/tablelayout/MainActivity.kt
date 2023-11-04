package com.tvt.tablelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.tvt.tablelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        addTableRow(version = "1.0", name = "iOS 1.0")
        addTableRow(version = "2.0", name = "iOS 2.0")
        addTableRow(version = "3.0", name = "iOS 3.0")
    }

    fun addTableRow(version: String, name: String) {
        val tableRow = TableRow(this)

        val layout = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT).apply {
            setMargins(0, 0, 5, 5)
        }

        val tvVersion = TextView(this)
        tvVersion.setBackgroundColor(getResources().getColor(R.color.cyan_200, theme))
        tvVersion.setTextColor(getResources().getColor(R.color.white, theme))
        tvVersion.setText(version)
        tvVersion.setPadding(20)
        tvVersion.layoutParams = layout

        val tvName = TextView(this)
        tvName.setBackgroundColor(getResources().getColor(R.color.cyan_200, theme))
        tvName.setTextColor(getResources().getColor(R.color.white, theme))
        tvName.setText(name)
        tvName.setPadding(20)

        val layoutParam = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        tableRow.layoutParams = layoutParam

        tableRow.addView(tvVersion, 0)
        tableRow.addView(tvName, 1)

        binding.tbView.addView(tableRow)
    }

    fun addRow(view: View) {
        val version = binding.etVersion.text.toString().trim()
        val name = binding.etName.text.toString().trim()

        if (version.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Version and Name are required", Toast.LENGTH_SHORT).show()
            return
        }
        addTableRow(version= version, name= name)
        binding.etVersion.text.clear()
        binding.etName.text.clear()
    }
}