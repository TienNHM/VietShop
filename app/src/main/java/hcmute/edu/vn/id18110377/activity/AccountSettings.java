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

    private boolean checkVerifyClicked = false;

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
                .setTitle("????ng xu???t")
                .setMessage("B???n c?? mu???n ????ng xu???t kh???i ???ng d???ng?")
                .setPositiveButton("C??", (dialogInterface, i) -> {
                    //AppUtilities.clearSession(view.getContext());
                    logout();
                    setLoginVisible();
                    Toast.makeText(this, "???? ????ng xu???t th??nh c??ng", Toast.LENGTH_SHORT).show();
                    updateUiIfLoggedIn();
                })
                .setNegativeButton("Kh??ng", null)
                .setIcon(R.drawable.shutdown)
                .show();
    }

    private void setLogoutVisible() {
        menuLogin.setVisibility(View.GONE);
        menuLogout.setVisibility(View.VISIBLE);
        menuUpdateAccount.setVisibility(View.VISIBLE);
        menuEmailVerified.setVisibility(View.GONE);
        if (checkVerifyClicked == false) checkVerify();
    }

    private void setLoginVisible() {
        menuLogout.setVisibility(View.GONE);
        menuLogin.setVisibility(View.VISIBLE);
        menuUpdateAccount.setVisibility(View.GONE);
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
            menuEmailVerified.setVisibility(View.VISIBLE);
            androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Th??ng b??o")
                    .setMessage("T??i kho???n c???a b???n ch??a ???????c x??c minh. V??i l??ng truy c???p email ???? ???????c d??ng ????ng k?? t??i kho???n ????? x??c nh???n.")
                    .setIcon(R.drawable.warning_32px)
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        Intent intent = new Intent(this, FirebaseActivity.class);
                        intent.putExtra(FirebaseActivity.EMAIL, currentUser.getEmail());
                        intent.setAction(FirebaseActivity.VERIFY_ACTION);
                        this.startActivityForResult(intent, FirebaseActivity.VERIFY);
                    })
                    .show();
            checkVerifyClicked = true;
        } else {
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
                Toast.makeText(this, "Vui l??ng ki???m tra email ????? x??c th???c t??i kho???n.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "???? c?? l???i ph??t sinh trong qu?? tr??nh g???i mail x??c th???c. Vui l??ng th??? l???i!", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == FirebaseActivity.FORGOT_PASSWORD) {
            if (resultCode == FirebaseActivity.FORGOT_PASSWORD_OK) {
                Toast.makeText(this, "Vui l??ng truy c???p email ????? ?????t l???i m???t kh???u.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
