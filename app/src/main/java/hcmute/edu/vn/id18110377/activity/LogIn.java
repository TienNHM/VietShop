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
import hcmute.edu.vn.id18110377.MainActivity;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.UserDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;
import hcmute.edu.vn.id18110377.entity.User;
import hcmute.edu.vn.id18110377.utilities.AppUtilities;

public class LogIn extends AppCompatActivity {
    @BindView(R.id.btnLogIn)
    Button btnLogin;
    @BindView(R.id.txtUsername)
    TextInputEditText txtUsername;
    @BindView(R.id.txtPassword)
    TextInputEditText txtPassword;
    @BindView(R.id.txtSignUp)
    TextView txtSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        btnLogin.setOnClickListener(this::setLogin);
        txtSignUp.setOnClickListener(this::setSignUp);
    }

    private void setLogin(View view) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        AccountDbHelper accountDbHelper = new AccountDbHelper(this);
        Account account = accountDbHelper.login(username, AppUtilities.encode(password));
        if (account != null) {
            MainActivity.account = account;
            UserDbHelper userDbHelper = new UserDbHelper(this);
            User user = userDbHelper.getUserByAccountId(account.getId());
            if (user != null) {
                MainActivity.user = user;
                AppUtilities.saveSession(this, username, password);

                Intent intent = new Intent(this, FirebaseActivity.class);
                intent.putExtra(FirebaseActivity.EMAIL, username);
                intent.putExtra(FirebaseActivity.PASSWORD, password);
                intent.setAction(FirebaseActivity.SIGN_IN_ACTION);
                startActivityForResult(intent, FirebaseActivity.SIGN_IN);

            } else
                Toast.makeText(this, "Đã có lỗi phát sinh trong quá trình đăng nhập.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Đăng nhập thất bại. Vui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
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
