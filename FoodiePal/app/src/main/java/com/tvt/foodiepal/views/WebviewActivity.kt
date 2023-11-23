package com.tvt.foodiepal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.tvt.foodiepal.R
import com.tvt.foodiepal.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val urlString = intent.getStringExtra("currentUrl") ?: ""
        if (urlString.isEmpty()) {
            return
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(urlString)
    }
}