package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import hcmute.edu.vn.id18110377.MainActivity;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;
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

        findViewById(R.id.btnBack_ChangeLanguage).setOnClickListener(view -> finish());
        btnLogin.setOnClickListener(this::setLogin);
    }

    private void setLogin(View view) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        AccountDbHelper accountDbHelper = new AccountDbHelper(this);
        Account account = accountDbHelper.login(username, AppUtilities.encode(password));
        if (account != null) {
            MainActivity.account = account;
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Đăng nhập thất bại. Vui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
        }
    }
}