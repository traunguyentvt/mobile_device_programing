package com.tvt.foodiepal.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.tvt.foodiepal.databinding.FragmentAboutMeBinding
import com.tvt.foodiepal.dialogs.AboutMeDialog
import com.tvt.foodiepal.listeners.DialogListener
import com.tvt.foodiepal.models.BaseModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutMeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutMeFragment : Fragment(), DialogListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAboutMeBinding
    private var changeImage: ActivityResultLauncher<Intent>? = null
    private var imgUri: Uri? = null
    private var strInformation: String? = null
    private var sharedPreferences: SharedPreferences? = null

    //No need to use for now
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        initChangeImage()
    }

    override fun onStart() {
        super.onStart()
        initChangeImage()
    }

    override fun onStop() {
        super.onStop()
//        changeImage = null
    }

    override fun onDestroy() {
        super.onDestroy()
        changeImage = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        initView()
        return binding.root
    }

    private fun initView() {
        binding.imv.setOnClickListener {
            if (changeImage != null) {
                val pickImg = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                )
                changeImage?.launch(pickImg)
            }
        }
        if (imgUri != null) {
            binding.imv.setImageURI(imgUri)
        }
        if (strInformation == null) {
            strInformation = binding.tvDesc.text.toString().trim()
        } else {
            binding.tvDesc.text = strInformation
        }
        if (sharedPreferences == null) {
            sharedPreferences = context?.getSharedPreferences("user_login", Context.MODE_PRIVATE)
        }
        val first_name = sharedPreferences?.getString("first_name", "") ?: "Van Ngoc Trau"
        var last_name = sharedPreferences?.getString("last_name", "") ?: "Nguyen"
        binding.tvAuthor.text = "$last_name, $first_name"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutMeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutMeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun onAdd() {
        val dialog = AboutMeDialog(this)
        dialog.show(parentFragmentManager, AboutMeDialog::class.java.name)
    }

    override fun addData(model: BaseModel) {
        var msg = binding.tvDesc.text.toString().trim() +
                "\n\n" + model.name + ": " + model.desc
        binding.tvDesc.text = msg
        strInformation = msg
    }

    private fun initChangeImage() {
        if (changeImage == null) {
            changeImage =
                registerForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) {
                    if (it.resultCode == Activity.RESULT_OK) {
                        val data = it.data
                        imgUri = data?.data
                        binding.imv.setImageURI(imgUri)
                    }
                }
        }
    }
}