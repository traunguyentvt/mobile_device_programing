package com.tvt.foodiepal.models

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList
import java.util.Date

data class MealPlannerModel(
    val date: Date,
    val meals: ArrayList<PlannerModel>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        Date(parcel.readLong()),
        arrayListOf<PlannerModel>().apply {
            parcel.readList(this, PlannerModel::class.java.classLoader)
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(date.time)
        parcel.writeArray(arrayOf(meals))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MealPlannerModel> {
        override fun createFromParcel(parcel: Parcel): MealPlannerModel {
            return MealPlannerModel(parcel)
        }

        override fun newArray(size: Int): Array<MealPlannerModel?> {
            return arrayOfNulls(size)
        }

        fun createMealPlanners(): ArrayList<MealPlannerModel> {
            return arrayListOf(
                MealPlannerModel(
                    Date(Date().time + 2*24*60*60*1000),
                    arrayListOf(
                        PlannerModel("Lunch", "Hamburger"),
                        PlannerModel("Dinner", "Stewed Pork")
                    )
                ),
                MealPlannerModel(
                    Date(Date().time + 24*60*60*1000),
                    arrayListOf(
                        PlannerModel("Breakfast", "Tuna Salad"),
                        PlannerModel("Lunch", "Rice Noodle"),
                        PlannerModel("Dinner", "Pizza")
                    )
                ),
                MealPlannerModel(
                    Date(),
                    arrayListOf(
                        PlannerModel("Breakfast", "Boiled Green Beans"),
                    )
                ),
                MealPlannerModel(
                    Date(Date().time - 24*60*60*1000),
                    arrayListOf(
                        PlannerModel("Breakfast", "Steamed Vegetables"),
                        PlannerModel("Dinner", "Roast Duck")
                    )
                ),
                MealPlannerModel(
                    Date(Date().time - 2*24*60*60*1000),
                    arrayListOf(
                        PlannerModel("Breakfast", "Coffee & Sandwich"),
                        PlannerModel("Lunch", "Beef Steak"),
                        PlannerModel("Dinner", "Rice Noodle")
                    )
                )
            )
        }
    }

}
