package com.adzkia.aos

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.NotificationCompat



class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the notification channel if the Android version is Oreo or higher.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        webView = findViewById(R.id.wb_webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://database.adzkiasumbar.or.id")

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        val webAppInterface = WebAppInterface(this)
        webView.addJavascriptInterface(webAppInterface, "Android")


    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        if (notificationManager != null) {
            val channel = NotificationChannel(
                "e_meeting",
                "E Meeting",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

}

class WebAppInterface(private val context: Context) {
    @JavascriptInterface
    fun showNotification(title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.notofocation_icon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1, builder.build())
    }

}
