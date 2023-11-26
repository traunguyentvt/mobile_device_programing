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

        fun createBlogs(): ArrayList<BlogModel> {
            return arrayListOf(
                BlogModel(
                    "Chicken Recipes",
                    "Find recipes for fried chicken, chicken breast, grilled chicken, chicken wings, and more! Allrecipes has more than 5,430 kitchen-approved chicken recipes.",
                    "https://www.allrecipes.com/recipes/201/meat-and-poultry/chicken"
                ),
                BlogModel(
                    "Duck Recipes",
                    "46 reasons why duck shouldn't be a dish you only order when dining out",
                    "https://www.delicious.com.au/recipes/collections/gallery/26-quick-duck-recipes-for-easy-midweek-meals/a8i11ayi"
                ),
                BlogModel(
                    "Beef Recipes",
                    "50 Quick and Easy Beef Recipes for Dinner",
                    "https://www.tasteofhome.com/collection/beef-recipes-for-dinner"
                )
            )
        }
    }

}
