package com.tvt.foodiepal.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tvt.foodiepal.databinding.FragmentContactBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentContactBinding

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
        binding = FragmentContactBinding.inflate(layoutInflater)
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
         * @return A new instance of fragment ContactFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun onAdd() {
        Toast.makeText(requireContext(), "TVT 4", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        binding.cvMobile.setOnClickListener {
            openMobilePhone()
        }
        binding.cvEmail.setOnClickListener {
            openEmail()
        }
        binding.cvLinkedin.setOnClickListener {
            openLinkedin()
        }
        binding.cvGithub.setOnClickListener {
            openGithub()
        }
    }

    fun openMobilePhone() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:6415570137")
        startActivity(intent)
    }

    fun openEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, "traunguyen92@gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contact via FoodiePal App")
        intent.putExtra(Intent.EXTRA_TEXT, "iOS Software Engineer")
        startActivity(intent)
    }

    fun openLinkedin() {
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra("currentUrl", "https://www.linkedin.com/in/trau-nguyen")
        startActivity(intent)
    }

    fun openGithub() {
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra("currentUrl", "https://github.com/traunguyentvt")
        startActivity(intent)
    }
}