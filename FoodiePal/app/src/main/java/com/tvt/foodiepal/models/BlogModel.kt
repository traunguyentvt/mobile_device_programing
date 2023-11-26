package com.tvt.foodiepal.models

import android.os.Parcel
import android.os.Parcelable

data class BlogModel(
    val name: String,
    val desc: String?,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BlogModel> {
        override fun createFromParcel(parcel: Parcel): BlogModel {
            return BlogModel(parcel)
        }

        override fun newArray(size: Int): Array<BlogModel?> {
            return arrayOfNulls(size)
        }
    }

}
