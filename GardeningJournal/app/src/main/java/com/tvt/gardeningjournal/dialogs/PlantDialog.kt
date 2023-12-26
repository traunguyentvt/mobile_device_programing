package com.tvt.gardeningjournal.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tvt.foodiepal.utilities.MinMaxFilter
import com.tvt.gardeningjournal.databinding.PlantDialogBinding
import com.tvt.gardeningjournal.listeners.DialogListener
import com.tvt.gardeningjournal.models.Plant
import com.tvt.gardeningjournal.models.dateFormatter
import java.util.Calendar
import java.util.Date

class PlantDialog(var listener: DialogListener): DialogFragment() {

    private lateinit var binding: PlantDialogBinding
    private val calendar = Calendar.getInstance()
    private var selectedDate: Date? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = PlantDialogBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            var builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        initViews()

        return dialog
    }

    private fun initViews() {
        binding.etWateringfrequency.filters = arrayOf<InputFilter>(MinMaxFilter(0.0, 100.0))

        binding.tvSelectedDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            addPlant()
        }
    }

    private fun addPlant() {
        val name = binding.etPlantName.text.toString().trim()
        if (name.isEmpty()) {
            showToast("Please input plant name")
            return
        }
        val type = binding.etPlantType.text.toString().trim()
        if (type.isEmpty()) {
            showToast("Please input plant type")
            return
        }
        val frequency = binding.etWateringfrequency.text.toString().trim()
        if (frequency.isEmpty()) {
            showToast("Please input watering date")
            return
        }
        val wateringFrequency = frequency.toDoubleOrNull() ?: 1.0

        if (selectedDate == null) {
            showToast("Please select planting date")
            return
        }

        val plant = Plant(0, name, type, wateringFrequency, dateFormatter.format(selectedDate))
        listener.addPlant(plant)

        dismiss()
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showDatePicker() {
        val datePickerDialog = activity?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display selected date in textbox
                    selectedDate = getDate(year, monthOfYear, dayOfMonth)
                    binding.tvSelectedDate.text = dateFormatter.format(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }

        datePickerDialog?.show()
    }

    private fun getDate(year: Int, month: Int, day: Int): Date {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.getTime()
    }
}