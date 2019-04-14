package com.saloni.doctoroncall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AiDiagnoseActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_diagnose);

        String URL = "https://symptomate.com/diagnosis/en/";

        webView = findViewById(R.id.webView);
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        assert webView != null;
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
//                assert progressBar != null;
//                progressBar.setVisibility(View.VISIBLE);
//                progressBar.setProgress(newProgress);
//                if (newProgress == 100) {
                    // Hide the progressbar
//                    progressBar.setVisibility(View.GONE);
//                }
            }
        });

        webView.loadUrl(URL);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(final WebView view, final String url) {
            view.loadUrl("javascript:document.getElementsByTagName" +
                    "('header')[0].setAttribute('hidden','');");
        }
    }
}
