package com.tvt.foodiepal.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.foodiepal.databinding.FragmentMealPlannerBinding
import com.tvt.foodiepal.dialogs.MealPlannerDialog
import com.tvt.foodiepal.listeners.DialogListener
import com.tvt.foodiepal.models.MealPlannerModel
import com.tvt.foodiepal.viewModels.MealPlannerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MealPlannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MealPlannerFragment : Fragment(), DialogListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMealPlannerBinding
    private lateinit var mealPlannerAdapter: MealPlannerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealPlannerBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        initViews()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MealPlannerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MealPlannerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun onAdd() {
        val dialog = MealPlannerDialog(this)
        dialog.show(parentFragmentManager, MealPlannerDialog::class.java.name)
    }

    private fun initViews() {
        if (!this::mealPlannerAdapter.isInitialized) {
            mealPlannerAdapter = MealPlannerAdapter()
            mealPlannerAdapter.setData(MealPlannerModel.createMealPlanners())
        }

        binding.rvMeals.adapter = mealPlannerAdapter
        binding.rvMeals.layoutManager = LinearLayoutManager(context)
        mealPlannerAdapter.reloadData()
    }

    override fun addMealPlanner(mealPlanner: MealPlannerModel) {
        mealPlannerAdapter.addMealPlanner(mealPlanner)
    }
}