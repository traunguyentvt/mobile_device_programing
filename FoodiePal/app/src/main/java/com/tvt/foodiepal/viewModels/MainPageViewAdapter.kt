package com.tvt.foodiepal.viewModels

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.tvt.foodiepal.views.AboutMeFragment
import com.tvt.foodiepal.views.BlogFragment
import com.tvt.foodiepal.views.ContactFragment
import com.tvt.foodiepal.views.MealPlannerFragment
import com.tvt.foodiepal.views.RecipeFragment

class MainPageViewAdapter(
    val fragmentActivity: FragmentActivity,
    val viewPager: ViewPager2
) : FragmentStateAdapter(fragmentActivity) {

    private var recipeFragment = RecipeFragment()
    private var mealPlannerFragment = MealPlannerFragment()
    private var blogFragment = BlogFragment()
    private var contactFragment = ContactFragment()
    private var aboutMeFragment = AboutMeFragment()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> recipeFragment
            1 -> mealPlannerFragment
            2 -> blogFragment
            3 -> contactFragment
            else -> aboutMeFragment
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun updateAdapter() {
        viewPager.adapter = this
    }

    fun setCurrentItem(index: Int) {
        if (viewPager.currentItem == index) {
            return
        }
        viewPager.setCurrentItem(index)
    }

    fun getCurrentItem(): Int {
        return viewPager.currentItem
    }

    fun onAdd() {
        when(getCurrentItem()) {
            0 -> recipeFragment.onAdd()
            1 -> mealPlannerFragment.onAdd()
            2 -> blogFragment.onAdd()
            3 -> contactFragment.onAdd()
            else -> aboutMeFragment.onAdd()
        }
    }
}