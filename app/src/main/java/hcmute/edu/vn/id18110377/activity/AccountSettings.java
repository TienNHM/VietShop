package hcmute.edu.vn.id18110377.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.utilities.AccountSessionManager;

import static hcmute.edu.vn.id18110377.utilities.AccountSessionManager.currentUser;
import static hcmute.edu.vn.id18110377.utilities.AccountSessionManager.logout;
import static hcmute.edu.vn.id18110377.utilities.AccountSessionManager.user;

public class AccountSettings extends AppCompatActivity {
    @BindView(R.id.menuLogin)
    LinearLayout menuLogin;
    @BindView(R.id.menuEmailVerified)
    LinearLayout menuEmailVerified;
    @BindView(R.id.menuForgotPassword)
    LinearLayout menuForgotPassword;
    @BindView(R.id.menuUpdateAccount)
    LinearLayout menuUpdateAccount;
    @BindView(R.id.menuLogout)
    LinearLayout menuLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ButterKnife.bind(this);

        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        menuEmailVerified.setOnClickListener(this::setCheckAccountVerified);
        menuLogin.setOnClickListener(this::setLoginClick);
        menuLogout.setOnClickListener(this::setLogoutClick);
        menuForgotPassword.setOnClickListener(this::setForgotPassword);
        menuUpdateAccount.setOnClickListener(this::updateAccountInfo);
        updateUiIfLoggedIn();
        if (user == null) {
            menuEmailVerified.setVisibility(View.GONE);
            menuUpdateAccount.setVisibility(View.GONE);
        }
    }

    private void setLoginClick(View view) {
        Intent intent = new Intent(this, LogIn.class);
        this.startActivity(intent);
        if (user != null) {
            setLogoutVisible();
            updateUiIfLoggedIn();
        }
    }

    private void setLogoutClick(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có muốn đăng xuất khỏi ứng dụng?")
                .setPositiveButton("Có", (dialogInterface, i) -> {
                    //AppUtilities.clearSession(view.getContext());
                    logout();
                    setLoginVisible();
                    Toast.makeText(this, "Đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Không", null)
                .setIcon(R.drawable.shutdown)
                .show();
    }

    private void setLogoutVisible() {
        menuLogin.setVisibility(View.GONE);
        menuLogin.setVisibility(View.GONE);
        menuLogout.setVisibility(View.VISIBLE);
    }

    private void setLoginVisible() {
        menuLogout.setVisibility(View.GONE);
        menuLogout.setVisibility(View.GONE);
        menuLogin.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUiIfLoggedIn();
    }

    private void updateUiIfLoggedIn() {
        if (user != null)
            setLogoutVisible();
        else setLoginVisible();
    }

    private void setCheckAccountVerified(View view) {
        checkVerify();
    }

    private void checkVerify() {
        boolean isVerified = AccountSessionManager.isEmailVerified();
        if (!isVerified) {
            androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Thông báo")
                    .setMessage("Tài khoản của bạn chưa được xác minh. Vùi lòng truy cập email đã được dùng đăng ký tài khoản để xác nhận.")
                    .setIcon(R.drawable.warning_32px)
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        Intent intent = new Intent(this, FirebaseActivity.class);
                        intent.putExtra(FirebaseActivity.EMAIL, currentUser.getEmail());
                        intent.setAction(FirebaseActivity.VERIFY_ACTION);
                        this.startActivityForResult(intent, FirebaseActivity.VERIFY);
                    })
                    .show();
        } else {
            Toast.makeText(this, "Đã xác thực", Toast.LENGTH_SHORT).show();
            menuEmailVerified.setVisibility(View.GONE);
        }
    }

    private void setForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivityForResult(intent, FirebaseActivity.FORGOT_PASSWORD);
    }

    private void updateAccountInfo(View view) {
        Intent intent = new Intent(this, AccountInfoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FirebaseActivity.VERIFY) {
            if (resultCode == FirebaseActivity.VERIFY_OK) {
                Toast.makeText(this, "Vui lòng kiểm tra email để xác thực tài khoản.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Đã có lỗi phát sinh trong quá trình gửi mail xác thực. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == FirebaseActivity.FORGOT_PASSWORD) {
            if (resultCode == FirebaseActivity.FORGOT_PASSWORD_OK) {
                Toast.makeText(this, "Vui lòng truy cập email để đặt lại mật khẩu.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
