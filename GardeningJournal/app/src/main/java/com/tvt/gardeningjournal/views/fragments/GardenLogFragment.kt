package com.tvt.gardeningjournal.views.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.gardeningjournal.adapters.GardenLogAdapter
import com.tvt.gardeningjournal.databinding.FragmentGardenLogBinding
import com.tvt.gardeningjournal.dialogs.PlantDialog
import com.tvt.gardeningjournal.listeners.DialogListener
import com.tvt.gardeningjournal.listeners.GardenLogListener
import com.tvt.gardeningjournal.models.Plant
import com.tvt.gardeningjournal.viewModels.GardenLogViewModel

class GardenLogFragment
    : BaseFragment<FragmentGardenLogBinding>(FragmentGardenLogBinding::inflate),
    GardenLogListener, DialogListener {

    private lateinit var viewModel: GardenLogViewModel
    private lateinit var gardenLogAdapter: GardenLogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)
            this@GardenLogFragment.context?.let {
                viewModel.initModel(it)
            }
        }
        if (!this::gardenLogAdapter.isInitialized) {
            gardenLogAdapter = GardenLogAdapter(this)
        }

        binding.rvGardenLog.layoutManager = LinearLayoutManager(context)
        binding.rvGardenLog.adapter = gardenLogAdapter

        viewModel.allPlants.observe(viewLifecycleOwner, Observer { plants ->
            plants?.let {
                if (it.size == 0) {
                    val sampleData = Plant.createGardenLogs()
                    gardenLogAdapter.setData(sampleData)
                    viewModel.insertAll(sampleData)
                } else {
                    gardenLogAdapter.setData(it)
                }
                gardenLogAdapter.notifyDataSetChanged()
            }
        })

        binding.btnAction.setOnClickListener {
            val dialog = PlantDialog(this)
            dialog.show(parentFragmentManager, PlantDialog::class.java.name)
        }
    }

    override fun viewGardenLog(plant: Plant) {
        val action = GardenLogFragmentDirections.navigateGardenLogToPlantDetail()
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun addPlant(plant: Plant) {
        viewModel.insert(plant)
    }
}