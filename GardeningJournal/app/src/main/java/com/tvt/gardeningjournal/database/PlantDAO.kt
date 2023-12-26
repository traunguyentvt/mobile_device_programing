package com.tvt.gardeningjournal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tvt.gardeningjournal.models.Plant

@Dao
interface PlantDAO {
    @Insert
    suspend fun insert(plant: Plant)

    @Insert
    suspend fun insertAll(plants: List<Plant>)

    @Update
    suspend fun update(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)

    @Query("DELETE FROM Plants WHERE id = :plantId")
    suspend fun deleteById(plantId: Int)

    @Query("DELETE FROM Plants")
    suspend fun deleteAll()

    @Query("SELECT * FROM Plants WHERE id = :plantId")
    fun getPlantById(plantId: Int): LiveData<Plant>

    @Query("SELECT * FROM Plants ORDER BY id DESC")
    fun getAllPlants(): LiveData<List<Plant>>
}