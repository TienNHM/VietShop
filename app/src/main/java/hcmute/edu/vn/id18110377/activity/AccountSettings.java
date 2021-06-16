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
    @BindView(R.id.menuLogout)
    LinearLayout menuLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        ButterKnife.bind(this);

        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        menuEmailVerified.setOnClickListener(this::setCheckAccountVerified);
        setLoginClick();
        setLogoutClick();
        updateUiIfLoggedIn();
    }

    private void setLoginClick() {
        menuLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, LogIn.class);
            this.startActivity(intent);
            if (user != null) {
                setLogoutVisible();
                updateUiIfLoggedIn();
            }
        });
    }

    private void setLogoutClick() {
        menuLogout.setOnClickListener(view1 -> {
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
        });
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
    }
}
