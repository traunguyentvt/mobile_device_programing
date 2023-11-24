package com.tvt.foodiepal.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tvt.foodiepal.databinding.MealPlannerDialogBinding
import com.tvt.foodiepal.listeners.DialogListener
import com.tvt.foodiepal.models.MealPlannerModel
import com.tvt.foodiepal.models.PlannerModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class MealPlannerDialog(var listener: DialogListener) : DialogFragment() {

    private lateinit var binding: MealPlannerDialogBinding
    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("EEEE, MM/dd/yyyy")
    private var selectedDate: Date? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = MealPlannerDialogBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            var builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        initViews()

        return dialog
    }

    private fun initViews() {
        binding.tvSelectedDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            addMealPlanner()
        }
    }

    private fun addMealPlanner() {
        if (selectedDate == null) {
            showToast("Please select a day")
            return
        }
        val breakfast = binding.etBreakfast.text.toString().trim()
        val lunch = binding.etLunch.text.toString().trim()
        val dinner = binding.etDinner.text.toString().trim()

        if (breakfast.isEmpty() && lunch.isEmpty() && dinner.isEmpty()) {
            showToast("Please input at least one meal for a day")
            return
        }

        var meals = ArrayList<PlannerModel>()
        if (!breakfast.isEmpty()) {
            meals.add(PlannerModel("Breakfast", breakfast))
        }
        if (!lunch.isEmpty()) {
            meals.add(PlannerModel("Lunch", lunch))
        }
        if (!dinner.isEmpty()) {
            meals.add(PlannerModel("Dinner", dinner))
        }
        var mealPlanner = MealPlannerModel(selectedDate ?: Date(), meals)
        listener.addMealPlanner(mealPlanner)

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