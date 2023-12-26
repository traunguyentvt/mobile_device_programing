package com.tvt.gardeningjournal.listeners

import com.tvt.gardeningjournal.models.Plant

interface GardenLogListener {
    fun viewGardenLog(plant: Plant)
}