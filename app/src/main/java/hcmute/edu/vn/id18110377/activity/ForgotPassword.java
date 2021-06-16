package hcmute.edu.vn.id18110377.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;

import static hcmute.edu.vn.id18110377.MainActivity.mainResources;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.EMAIL;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.FORGOT_PASSWORD;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.FORGOT_PASSWORD_ACTION;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.FORGOT_PASSWORD_OK;

public class ForgotPassword extends AppCompatActivity {
    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.email)
    TextInputEditText txtEmail;
    @BindView(R.id.btnGetPassword)
    Button btnGetPassword;
    private final static String message = mainResources.getString(R.string.message_forgot_password);
    @BindView(R.id.txtLabel)
    TextView txtLabel;
    @BindView(R.id.layoutGetPassword)
    LinearLayout layoutGetPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(view -> finish());
        btnGetPassword.setOnClickListener(this::setGetPassword);
        txtLabel.setVisibility(View.GONE);
    }

    private void setGetPassword(View view) {
        String email = txtEmail.getText().toString();
        if (!email.contains("@")) {
            Toast.makeText(this, "Vui lòng nhập chính xác email.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, FirebaseActivity.class);
        intent.putExtra(EMAIL, email);
        intent.setAction(FORGOT_PASSWORD_ACTION);
        startActivityForResult(intent, FORGOT_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FORGOT_PASSWORD) {
            if (resultCode == FORGOT_PASSWORD_OK) {
                Toast.makeText(this, String.format(message, txtEmail.getText()), Toast.LENGTH_SHORT).show();
                txtLabel.setText(String.format(message, txtEmail.getText()));
                txtLabel.setVisibility(View.VISIBLE);
                layoutGetPassword.setVisibility(View.GONE);
            }
        }
    }
}
