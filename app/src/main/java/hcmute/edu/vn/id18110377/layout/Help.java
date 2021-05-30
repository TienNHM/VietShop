package hcmute.edu.vn.id18110377.layout;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.id18110377.R;

public class Help extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_help);

        ImageButton ibtn = findViewById(R.id.btnBack_help);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        WebView webView = findViewById(R.id.webHelp);
        webView.loadUrl("https://github.com/tiennhm");
    }
}
