package com.example.predictcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Solana_Coin extends AppCompatActivity {

    private WebView webView;
    private WebView webView2;
    private WebView webView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solana_coin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.sol_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new Solana_Coin.browser());
        webView.loadUrl("http://13.124.225.127/sol_close_graph.php");

        webView2 = (WebView) findViewById(R.id.sol_webview2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setVerticalScrollBarEnabled(false);
        webView2.setHorizontalScrollBarEnabled(false);
        webView2.setWebViewClient(new Solana_Coin.browser());
        webView2.loadUrl("http://13.124.225.127/sol_close_FP.php");

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return (motionEvent.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        webView3 = (WebView) findViewById(R.id.sol_lastcost);
        webView3.getSettings().setJavaScriptEnabled(true);
        webView3.loadUrl("http://13.124.225.127/sol_last.php");
    }

    private class browser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void test (View view) {
        switch (view.getId()) {
            case R.id.sol_a:
                webView.loadUrl("http://13,124,225,127/sol_close_graph.php");
                webView2.loadUrl("http://13.124.225.127/sol_close_FP.php");
                break;
            case R.id.sol_b:
                webView.loadUrl("http://13.124.225.127/sol_low_graph.php");
                webView2.loadUrl("http://13.124.225.127/sol_low_FP.php");
                break;
            case R.id.sol_c:
                webView.loadUrl("http://13.124.225.127/sol_high_graph.php");
                webView2.loadUrl("http://13.124.225.127/sol_high_FP.php");
                break;
        }
    }


    // backspace
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}