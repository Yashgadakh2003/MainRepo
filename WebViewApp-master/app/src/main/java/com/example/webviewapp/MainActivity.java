package com.example.webviewapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // Load inside app
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        webView.loadUrl("https://www.google.co.in/"); // Replace with your desired URL
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack(); // Go back in WebView history
        } else {
            super.onBackPressed();
        }
    }
}
