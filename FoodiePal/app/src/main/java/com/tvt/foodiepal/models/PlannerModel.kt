package com.tvt.foodiepal.models

import android.os.Parcel
import android.os.Parcelable

data class PlannerModel(
    val name: String,
    val meal: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(meal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlannerModel> {
        override fun createFromParcel(parcel: Parcel): PlannerModel {
            return PlannerModel(parcel)
        }

        override fun newArray(size: Int): Array<PlannerModel?> {
            return arrayOfNulls(size)
        }
    }

}
