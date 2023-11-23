package com.tvt.foodiepal.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.foodiepal.databinding.FragmentRecipeBinding
import com.tvt.foodiepal.dialogs.RecipeDialog
import com.tvt.foodiepal.listeners.DialogListener
import com.tvt.foodiepal.listeners.RecipeListener
import com.tvt.foodiepal.models.RecipeModel
import com.tvt.foodiepal.viewModels.RecipeAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeFragment : Fragment(), RecipeListener, DialogListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var recipeAdapter: RecipeAdapter

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
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(layoutInflater)
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
         * @return A new instance of fragment RecipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initViews() {
        if (!this::recipeAdapter.isInitialized) {
            recipeAdapter = RecipeAdapter(this)
        }
        binding.rvRecipe.adapter = recipeAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(context)

        recipeAdapter.setData(RecipeModel.createRecipes())
    }

    fun onAdd() {
        val dialog = RecipeDialog(this)
        dialog.show(parentFragmentManager, RecipeDialog::class.java.name)
    }

    override fun viewRecipe(recipe: RecipeModel) {
        val url = recipe.url ?: ""
        if (url.isEmpty()) {
            return
        }
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra("currentUrl", url)
        startActivity(intent)
    }

    override fun addRecipe(recipe: RecipeModel) {
        recipeAdapter.addRecipe(recipe)
    }
}