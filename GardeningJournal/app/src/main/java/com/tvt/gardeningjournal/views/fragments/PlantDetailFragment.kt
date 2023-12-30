package com.tvt.gardeningjournal.views.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.tvt.gardeningjournal.databinding.FragmentPlantDetailBinding
import com.tvt.gardeningjournal.models.Plant
import com.tvt.gardeningjournal.viewModels.PlantDetailViewModel

class PlantDetailFragment : BaseFragment<FragmentPlantDetailBinding>(FragmentPlantDetailBinding::inflate) {

    private lateinit var viewModel: PlantDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this).get(PlantDetailViewModel::class.java)
            context?.let {
                viewModel.initModel(it)
            }
        }
        val plantId = arguments?.getInt("plantId") ?: 0
        viewModel.getPlantById(plantId).observe(viewLifecycleOwner, Observer { plant ->
            displayPlantDetail(plant)
        })
    }

    private fun displayPlantDetail(plant: Plant) {
        binding.tvName.text = plant.name
        binding.tvType.text = "Type: ${plant.type}"
        binding.tvWateringfrequency.text = "Watering Frequency: ${plant.wateringFrequency} days"
        binding.tvPlantingDate.text = "Planting Date: ${plant.plantingDate}"
    }

}