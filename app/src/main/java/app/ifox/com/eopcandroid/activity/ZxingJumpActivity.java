package app.ifox.com.eopcandroid.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import app.ifox.com.eopcandroid.R;

/**
 * Created by 13118467271 on 2018/3/7.
 */

public class ZxingJumpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView backText;
    private WebView webView;
    private String zxingUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxing_jump);
        zxingUrl = getIntent().getStringExtra("url");
        init();
    }

    private void init() {
        back = (Button) findViewById(R.id.zxing_jump_back_button);
        backText = (TextView) findViewById(R.id.zxing_jump_back_text);
        webView = (WebView) findViewById(R.id.zxing_jump_webview);
        back.setOnClickListener(this);
        backText.setOnClickListener(this);
        setWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(zxingUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zxing_jump_back_button:
            case R.id.zxing_jump_back_text:
                Intent backIntent = new Intent(ZxingJumpActivity.this,MapActivity.class);
                startActivity(backIntent);
                finish();
                break;
            default:
                    break;

        }
    }
}
