package hcmute.edu.vn.id18110377.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.utilities.AccountSessionManager;
import hcmute.edu.vn.id18110377.utilities.AppUtilities;

public class LogIn extends AppCompatActivity {
    @BindView(R.id.btnLogIn)
    Button btnLogin;
    @BindView(R.id.email)
    TextInputEditText txtEmail;
    @BindView(R.id.txtPassword)
    TextInputEditText txtPassword;
    @BindView(R.id.txtSignUp)
    TextView txtSignUp;
    @BindView(R.id.btnForgotPassword)
    Button btnForgotPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        btnLogin.setOnClickListener(this::setLogin);
        txtSignUp.setOnClickListener(this::setSignUp);
        btnForgotPassword.setOnClickListener(this::setForgotPassword);
    }

    private void setLogin(View view) {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        Intent intent = new Intent(this, FirebaseActivity.class);
        intent.putExtra(FirebaseActivity.EMAIL, email);
        intent.putExtra(FirebaseActivity.PASSWORD, AppUtilities.encode(password));
        intent.setAction(FirebaseActivity.SIGN_IN_ACTION);
        startActivityForResult(intent, FirebaseActivity.SIGN_IN);

        if (AccountSessionManager.user != null) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setSignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }

    private void setForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivityForResult(intent, FirebaseActivity.FORGOT_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FirebaseActivity.SIGN_IN) {
            if (resultCode == FirebaseActivity.SIGN_IN_OK) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
