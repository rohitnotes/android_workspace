package com.example.sec_7_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient()); // Open up the webviewer from user's phone

        //webView.loadUrl("http://www.google.com");

        webView.loadData("<html><body><h1> /n/n/n Hello World </h1></body></html>", "text/html", "UTF-8" );


    }
}
