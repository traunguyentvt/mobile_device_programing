package com.tvt.foodiepal.models

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.tvt.foodiepal.R

data class RecipeModel(
    val name: String,
    val ingredients: String,
    val instructions: String,
    val rating: Double,
    val image: Int,
    val url: String? = null,
    val imgUri: Uri? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(ingredients)
        parcel.writeString(instructions)
        parcel.writeDouble(rating)
        parcel.writeInt(image)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeModel> {
        override fun createFromParcel(parcel: Parcel): RecipeModel {
            return RecipeModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeModel?> {
            return arrayOfNulls(size)
        }

        fun createRecipes(): ArrayList<RecipeModel> {
            return arrayListOf(
                RecipeModel("Roast Duck",
                    "6 lb whole Pekin duck; salt; 5 garlic cloves chopped; 1 lemon small or medium, chopped; ½ cup balsamic vinegar; 1 lemon , freshly squeezed juice; ¼ cup honey",
                    "Step 1: Roast for 40 minutes. Step 2: Roast for 20 minutes (or up to 40 minutes). Step 3: Remove duck fat. Step 4: Make a honey-balsamic glaze and roast for 20 minutes, brushing the duck with the glaze. Step 5: Remove the duck from the oven.",
                    5.0,
                    R.drawable.roast_duck,
                    "https://juliasalbum.com/how-to-cook-duck"),
                RecipeModel("Beef Steak",
                    "4 pounds New York strip steak, sliced thin; 1 lemon, juiced; 3 tablespoons soy sauce; 1 teaspoon white sugar; 1 tablespoon cornstarch; ¼ cup vegetable oil; 3 tablespoons olive oil",
                    "Step 1: Place sliced beef in a large bowl. Whisk together lemon juice, soy sauce, sugar, salt, and pepper in a small bowl. Step 2: Heat vegetable oil in a large skillet over medium heat. Step 3: Remove beef slices from marinade, shaking to remove any excess liquid. Discard marinade.",
                    4.9,
                    R.drawable.beef_steak),
                RecipeModel("Steamed Vegetables",
                    "1 head broccoli, florets only; 1 cup baby carrots; 1 cup sugar snap peas; 4 tablespoons unsalted butter; 1 tablespoon freshly minced garlic; salt and black pepper to taste",
                    "Step 1: Steam the vegetables for about 8 minutes or until done to your liking. When done to your liking, remove the vegetables to a serving bowl and set aside while you make the garlic butter. Step 2: Pour out the water in the saucepan and place the saucepan over mid-low heat. Melt 4 tablespoons butter and saute 1 tablespoon freshly minced garlic until fragrant. About a minute. Add about a 1/2 teaspoon of salt and a 1/4 teaspoon of pepper and stir to combine. Give it a taste and adjust the seasoning as needed. Drizzle the garlic butter over the vegetables and enjoy!",
                    4.7,
                    R.drawable.steamed_vegetables,
                    "https://www.ourlifetastesgood.com/2015/08/steamed-vegetables-with-garlic-butter.html"),
                RecipeModel("Boiled Green Beans",
                    "Fresh green beans, Kosher salt, Butter",
                    "Step 1: Drop the beans into salted boiling water. Cook them for about 5 minutes. Step 2: Drain the beans and season them with salt. Step 3: Serve them topped with butter.",
                    4.6,
                    R.drawable.boiled_green_beans,
                    "https://healthyrecipesblogs.com/how-to-cook-green-beans")
            )
        }
    }

}