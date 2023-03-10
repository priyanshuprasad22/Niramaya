package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview_detail extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_detail);

        web=findViewById(R.id.web_view);
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String url=getIntent().getExtras().getString("url");

        web.setWebViewClient(new Callback());
        web.loadUrl(url);


    }

    private class Callback extends WebViewClient {

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;

        }
    }
}