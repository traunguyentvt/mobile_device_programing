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

class MainPageViewAdapter(val fragmentActivity: FragmentActivity, val viewPager: ViewPager2) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RecipeFragment()
            1 -> MealPlannerFragment()
            2 -> BlogFragment()
            3 -> ContactFragment()
            else -> AboutMeFragment()
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
}