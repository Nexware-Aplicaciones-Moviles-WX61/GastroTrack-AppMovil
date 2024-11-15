package com.example.gastrotrack_appmovil.activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R

class TermsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.freeprivacypolicy.com/live/9ad73421-60bf-4fb0-b5d0-0b98d03b82c5")

        val btnBackToSignUp = findViewById<Button>(R.id.btnBackToSignUp)
        btnBackToSignUp.setOnClickListener {
            finish()
        }
    }
}
