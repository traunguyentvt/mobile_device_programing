package com.tvt.gardeningjournal.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

val dateFormatter = SimpleDateFormat("yyyy-MM-dd")

@Entity(tableName = "Plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: String,
    @ColumnInfo(name = "wateringFrequency")
    val wateringFrequency: Double,
    val plantingDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeDouble(wateringFrequency)
        parcel.writeString(plantingDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plant> {
        override fun createFromParcel(parcel: Parcel): Plant {
            return Plant(parcel)
        }

        override fun newArray(size: Int): Array<Plant?> {
            return arrayOfNulls(size)
        }

        fun createGardenLogs(): List<Plant> {
            return arrayListOf(
                Plant(1, "Rose", "Flower", 2.0, dateFormatter.format(Date(Date().time + 24*60*60*1000))),
                Plant(2, "Tomato", "Vegetable", 3.0, dateFormatter.format(Date(Date().time - 24*60*60*1000))),
                Plant(3, "Basil", "Herb", 2.0, dateFormatter.format(Date(Date().time + 2*24*60*60*1000)))
            )
        }
    }
}
