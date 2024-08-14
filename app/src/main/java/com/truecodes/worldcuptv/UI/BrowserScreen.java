package com.truecodes.worldcuptv.UI;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;

public class BrowserScreen extends AppCompatActivity {
    FrameLayout frameLayout;
    WebView mywebview;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_browser_screen);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.webViewProgressBar);
        this.progressBar = progressBar;
        progressBar.setVisibility(View.VISIBLE);
        load();
    }

    public void load() {
        WebView webView = findViewById(R.id.webviewid);
        this.mywebview = webView;
        webView.loadUrl(getIntent().getStringExtra(ImagesContract.URL));
        WebSettings settings = this.mywebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        this.mywebview.setScrollBarStyle(33554432);
        this.mywebview.setScrollbarFadingEnabled(true);
        this.mywebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView webView2, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            }

            @Override
            public void onPageFinished(WebView webView2, String str) {
                BrowserScreen.this.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (this.mywebview.canGoBack()) {
            this.mywebview.goBack();
        } else {
            finish();
        }
    }
}