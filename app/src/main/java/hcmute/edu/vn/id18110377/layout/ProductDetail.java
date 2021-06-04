package hcmute.edu.vn.id18110377.layout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.id18110377.R;

public class ProductDetail extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        findViewById(R.id.btnBack_detail).setOnClickListener(v -> {
            finish();
        });
    }

}