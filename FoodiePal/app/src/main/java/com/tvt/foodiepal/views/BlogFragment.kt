package com.tvt.foodiepal.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.foodiepal.databinding.FragmentBlogBinding
import com.tvt.foodiepal.dialogs.BlogDialog
import com.tvt.foodiepal.listeners.BlogListener
import com.tvt.foodiepal.listeners.DialogListener
import com.tvt.foodiepal.models.BlogModel
import com.tvt.foodiepal.viewModels.BlogAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlogFragment : Fragment(), DialogListener, BlogListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var bindingBlogBinding: FragmentBlogBinding
    private lateinit var blogAdapter: BlogAdapter

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
        bindingBlogBinding = FragmentBlogBinding.inflate(layoutInflater)
        initViews()
        return bindingBlogBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initViews() {
        if (!this::blogAdapter.isInitialized) {
            blogAdapter = BlogAdapter(this)
            blogAdapter.setData(BlogModel.createBlogs())
        }
        bindingBlogBinding.rvBlog.layoutManager = LinearLayoutManager(context)
        bindingBlogBinding.rvBlog.adapter = blogAdapter
        blogAdapter.reloadData()
    }

    fun onAdd() {
        val dialog = BlogDialog(this)
        dialog.show(parentFragmentManager, BlogFragment::class.java.name)
    }

    override fun addBlog(blogModel: BlogModel) {
        blogAdapter.addBlog(blogModel)
    }

    override fun viewBlog(blog: BlogModel) {
        val url = blog.url ?: ""
        if (url.isEmpty()) {
            return
        }
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra("currentUrl", url)
        startActivity(intent)
    }
}