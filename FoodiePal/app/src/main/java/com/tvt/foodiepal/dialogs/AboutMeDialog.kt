package com.tvt.foodiepal.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.tvt.foodiepal.R
import com.tvt.foodiepal.databinding.AboutmeDialogBinding
import com.tvt.foodiepal.listeners.DialogListener
import com.tvt.foodiepal.models.AboutMeModel
import com.tvt.foodiepal.models.BaseModel

class AboutMeDialog(var listener: DialogListener): DialogFragment() {

    private lateinit var binding: AboutmeDialogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AboutmeDialogBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        initViews()

        return dialog
    }

    private fun initViews() {
        binding.btnCancel.setOnClickListener{
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            addRecord()
        }
    }

    private fun addRecord() {
        val subject = binding.etSubject.text.toString().trim()
        if (subject.isEmpty()) {
            showToast(resources.getString(R.string.txt_please_input_title))
            return
        }
        val desc = binding.etDesc.text.toString().trim()
        if (subject.isEmpty()) {
            showToast(resources.getString(R.string.txt_please_input_desc))
            return
        }
        listener.addData(BaseModel(subject, desc))
        dismiss()
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}