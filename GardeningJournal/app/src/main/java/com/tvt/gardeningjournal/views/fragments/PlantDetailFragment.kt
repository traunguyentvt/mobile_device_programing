package com.tvt.gardeningjournal.views.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import com.tvt.gardeningjournal.databinding.FragmentPlantDetailBinding
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
        }
    }

}