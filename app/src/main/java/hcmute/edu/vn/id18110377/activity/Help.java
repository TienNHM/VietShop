package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.id18110377.R;

public class Help extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_help);

        findViewById(R.id.btnBack_help).setOnClickListener(view -> finish());
        WebView webView = findViewById(R.id.webHelp);
        String url = getResources().getString(R.string.help_url);
        webView.loadUrl(url);
    }
}
