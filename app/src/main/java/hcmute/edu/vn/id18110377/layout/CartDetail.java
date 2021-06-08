package hcmute.edu.vn.id18110377.layout;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.id18110377.R;

public class CartDetail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);

        ImageButton img = findViewById(R.id.btnBack);
        img.setOnClickListener(v -> {
            finish();
        });
    }
}
