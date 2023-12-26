package com.tvt.gardeningjournal.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tvt.gardeningjournal.database.PlantDAO
import com.tvt.gardeningjournal.database.PlantDatabase
import com.tvt.gardeningjournal.models.Plant
import kotlinx.coroutines.launch

class GardenLogViewModel() : ViewModel() {

    lateinit var allPlants: LiveData<List<Plant>>
    private lateinit var plantDAO: PlantDAO

    fun initModel(context: Context) {
        plantDAO = PlantDatabase(context).getPlantDAO()
        allPlants = plantDAO.getAllPlants()
    }

    fun insertAll(plants: List<Plant>) {
        viewModelScope.launch {
            plantDAO.insertAll(plants)
        }
    }

    fun insert(plant: Plant) {
        viewModelScope.launch {
            plantDAO.insert(plant)
        }
    }
}