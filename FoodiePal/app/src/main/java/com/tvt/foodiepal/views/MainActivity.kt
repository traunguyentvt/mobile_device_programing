package com.tvt.foodiepal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.isGone
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tvt.foodiepal.R
import com.tvt.foodiepal.databinding.ActivityMainBinding
import com.tvt.foodiepal.viewModels.MainPageViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pageAdapter: MainPageViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initViewPagers()
        initTabLayout()
        initBottomNavigationView()
    }

    private fun initViewPagers() {
        if (!this::pageAdapter.isInitialized) {
            pageAdapter = MainPageViewAdapter(this, binding.vpager)
        }
        pageAdapter.updateAdapter()
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, pageAdapter.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getText(R.string.txt_recipes)
                }

                1 -> {
                    tab.text = resources.getText(R.string.txt_meal_planner)
                }

                2 -> {
                    tab.text = resources.getText(R.string.txt_blog)
                }

                3 -> {
                    tab.text = resources.getText(R.string.txt_contact)
                }

                else -> {
                    tab.text = resources.getText(R.string.txt_about_me)
                }
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text) {
                    resources.getText(R.string.txt_contact) -> unSelectedBottomNavigationView(3)
                    resources.getText(R.string.txt_about_me) -> unSelectedBottomNavigationView(4)
                    else -> selectedBottomNavigationView(tab?.text.toString())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun unSelectedBottomNavigationView(index: Int) {
        updateBtnAction(index)
        binding.bottomNavigationView.getMenu().forEach { item ->
            item.setCheckable(false)
        }
    }

    private fun updateBtnAction(index: Int) {
        if (index == 3) {
            binding.btnAction.isGone = true
        } else {
            binding.btnAction.isGone = false
        }
    }

    private fun selectedBottomNavigationView(name: String) {
        updateBtnAction(0)
        binding.bottomNavigationView.getMenu().forEach { item ->
            if (item.title.toString() == name) {
                item.setCheckable(true)
                binding.bottomNavigationView.selectedItemId = item.itemId
            } else {
                item.setCheckable(false)
            }
        }
    }

    private fun initBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            updateBtnAction(0)
            when (it.itemId) {
                R.id.recipe -> pageAdapter.setCurrentItem(0)
                R.id.mealPlanner -> pageAdapter.setCurrentItem(1)
                else -> pageAdapter.setCurrentItem(2)
            }
            true
        }
    }

    fun onAdd(view: View) {
        pageAdapter.onAdd()
    }
}