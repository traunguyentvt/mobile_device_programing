package com.tvt.gardeningjournal.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tvt.gardeningjournal.database.PlantDAO
import com.tvt.gardeningjournal.database.PlantDatabase
import com.tvt.gardeningjournal.models.Plant

class PlantDetailViewModel : ViewModel() {

    private lateinit var plantDAO: PlantDAO

    fun initModel(context: Context) {
        plantDAO = PlantDatabase(context).getPlantDAO()
    }

    fun getPlantById(plantId: Int) : LiveData<Plant> {
        return plantDAO.getPlantById(plantId)
    }

}