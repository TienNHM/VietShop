package hcmute.edu.vn.id18110377.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;

public class ForgotPassword extends AppCompatActivity {
    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.email)
    TextInputEditText txtEmail;
    @BindView(R.id.btnGetPassword)
    Button btnGetPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(view -> finish());
        btnGetPassword.setOnClickListener(this::setGetPassword);
    }

    private void setGetPassword(View view) {
        String email = txtEmail.getText().toString();
        if (!email.contains("@")) {
            Toast.makeText(this, "Vui lòng nhập chính xác email.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, FirebaseActivity.class);
        intent.putExtra(FirebaseActivity.EMAIL, email);
        intent.setAction(FirebaseActivity.FORGOT_PASSWORD_ACTION);
        startActivityForResult(intent, FirebaseActivity.FORGOT_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FirebaseActivity.FORGOT_PASSWORD) {
            if (resultCode == FirebaseActivity.FORGOT_PASSWORD_OK) {
                Toast.makeText(this, "Vui lòng truy cập email để nhận lại mật khẩu.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
