package com.tvt.foodiepal.models

import android.os.Parcel
import android.os.Parcelable

open class BaseModel(
    val name: String,
    val desc: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseModel> {
        override fun createFromParcel(parcel: Parcel): BaseModel {
            return BaseModel(parcel)
        }

        override fun newArray(size: Int): Array<BaseModel?> {
            return arrayOfNulls(size)
        }
    }

}