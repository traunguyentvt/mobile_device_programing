package com.tvt.gardeningjournal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tvt.gardeningjournal.models.Plant

@Database(
    entities = [Plant::class],
    version = 1,
    exportSchema = false
)
abstract class PlantDatabase(): RoomDatabase() {
    abstract fun getPlantDAO(): PlantDAO

    companion object {
        @Volatile
        private var instance: PlantDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PlantDatabase::class.java,
            "plant_database"
        ).build()
    }
}