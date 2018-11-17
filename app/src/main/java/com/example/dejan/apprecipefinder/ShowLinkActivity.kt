package com.example.dejan.apprecipefinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_show_link.*
import android.webkit.WebViewClient



class ShowLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        var extras = intent.extras

        if (extras != null) {
            var link = extras.get("link")

            // this will load url into web view component we added to activity_show_link.xml
            webViewID.setWebViewClient(WebViewClient())
            webViewID.loadUrl(link.toString())

        }

    }
}
