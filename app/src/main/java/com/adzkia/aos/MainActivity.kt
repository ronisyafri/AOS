package com.adzkia.aos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.wb_webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://database.adzkiasumbar.or.id")

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        //val webAppInterface = WebAppInterface(this)
        //webView.addJavascriptInterface(webAppInterface, "Android")


    }
}
