package com.tvt.gardeningjournal.listeners

import com.tvt.gardeningjournal.models.Plant

interface DialogListener {
    fun addPlant(plant: Plant) {}
}