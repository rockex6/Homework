package com.rockex6.homework.ui.webview

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import com.rockex6.homework.BaseActivity
import com.rockex6.homework.api.Logger
import com.rockex6.homework.databinding.ActivityWebBinding

class WebActivity : BaseActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var _binding: ActivityWebBinding

    private val webUrl: String
        get() = intent.extras!!.getString(KEY_WEB_URL, "")

    private val webTitle: String
        get() = intent.extras!!.getString(KEY_WEB_TITLE, "")

    companion object {
        const val KEY_WEB_URL = "web_url"
        const val KEY_WEB_TITLE = "web_title"
        fun getWebActivityBundle(title: String, url: String): Bundle {
            return bundleOf(KEY_WEB_TITLE to title, KEY_WEB_URL to url)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        init()
        initToolbar(webTitle, _binding.vToolbar, true)
    }

    private fun init() {
        Logger.d(TAG, "web url : $webUrl")
        if (webUrl.isEmpty()) {
            finish()
        }
        _binding.vWebView.apply {
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            loadUrl(webUrl)
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    _binding.vProgressBar.visibility = View.GONE
                }
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (_binding.vWebView.canGoBack()) {
            _binding.vWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}