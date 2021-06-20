package hcmute.edu.vn.id18110377.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.UserDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;
import hcmute.edu.vn.id18110377.entity.User;
import hcmute.edu.vn.id18110377.utilities.AppUtilities;
import hcmute.edu.vn.id18110377.utilities.ImageConverter;

import static hcmute.edu.vn.id18110377.utilities.AppUtilities.PERMISSION_REQUEST_CODE;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.SELECT_PICTURE;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.TAKE_PICTURE;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.encode;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.requestPermission;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.setPic;

public class SignUp extends AppCompatActivity {
    @BindView(R.id.txtFullName)
    TextInputEditText txtFullName;
    @BindView(R.id.txtEmail)
    TextInputEditText txtEmail;
    @BindView(R.id.txtPhone)
    TextInputEditText txtPhone;
    @BindView(R.id.txtAddress)
    TextInputEditText txtAddress;
    @BindView(R.id.txtUsername)
    TextInputEditText txtUsername;
    @BindView(R.id.txtPassword)
    TextInputEditText txtPassword;
    @BindView(R.id.txtConfirmPassword)
    TextInputEditText txtConfirmPassword;
    @BindView(R.id.layoutConfirmPassword)
    TextInputLayout layoutConfirmPassword;
    @BindView(R.id.imgAvt)
    ImageView imgAvt;
    @BindView(R.id.chipGroupSex)
    ChipGroup chipGroupSex;

    public static boolean isIntentAvailable(@NotNull Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 23) {
            if (!AppUtilities.checkPermission(this))
                requestPermission(this);
        }

        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        findViewById(R.id.txtSignIn).setOnClickListener(this::setLogIn);
        findViewById(R.id.btnSignUp).setOnClickListener(this::setSignUp);
        findViewById(R.id.btnTakePhoto).setOnClickListener(AppUtilities::setTakePhoto);
        findViewById(R.id.btnChoosePhoto).setOnClickListener(AppUtilities::setChoosePhoto);
        setConfirmPassword();
    }

    private void setConfirmPassword() {
        txtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setConfirmPasswordErrorHelper();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setConfirmPasswordErrorHelper();
            }
        });
    }

    private void setConfirmPasswordErrorHelper() {
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();

        if (password.equals(confirmPassword))
            layoutConfirmPassword.setErrorEnabled(false);
        else {
            layoutConfirmPassword.setErrorEnabled(true);
            layoutConfirmPassword.setError("Phải trùng với mật khẩu đã nhập.");
        }
    }

    private boolean validate(@NotNull String fullName, String email, String phone, String address,
                             String username, String password, String confirmPassword) {
        if (fullName.equals("")) return false;
        if (email.equals("")) return false;
        if (phone.equals("")) return false;
        if (address.equals("")) return false;
        if (username.equals("")) return false;
        if (password.equals("")) return false;
        if (confirmPassword.equals("")) return false;
        if (!password.equals(confirmPassword)) return false;
        return password.length() >= 6;
    }

    private void setSignUp(View view) {
        String fullName = txtFullName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String address = txtAddress.getText().toString();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();
        Bitmap avatar = ImageConverter.drawable2Bitmap(imgAvt.getDrawable());

        if (!validate(fullName, email, phone, address, username, password, confirmPassword)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin cá nhân!", Toast.LENGTH_SHORT).show();
            return;
        }

        Account account = new Account(username, email, encode(password));
        AccountDbHelper accountDbHelper = new AccountDbHelper(this);
        long rowID = accountDbHelper.insert(account);
        if (rowID < 0) {
            Toast.makeText(this, "Vui lòng nhập lại thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            Integer accountId = accountDbHelper.getAccountByRowId(rowID).getId();
            User user = new User(accountId, fullName, getSex(), phone, address, avatar);
            UserDbHelper userDbHelper = new UserDbHelper(this);
            long re = userDbHelper.insert(user);
            if (re < 0) {
                Toast.makeText(this, "Đã xảy ra lỗi trong quá trình tạo tài khoản. Vui lòng tạo lại!",
                        Toast.LENGTH_SHORT).show();
            } else {
                //AppUtilities.saveSession(this, username, password);
                createFirebaseUser(email, password);
                Toast.makeText(this, "Đã đăng ký thành công! Vui lòng xác thực email để sử dụng đầy đủ các chức năng.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void createFirebaseUser(String email, String password) {
        Intent intent = new Intent(this, FirebaseActivity.class);
        intent.putExtra(FirebaseActivity.EMAIL, email);
        intent.putExtra(FirebaseActivity.PASSWORD, password);
        intent.setAction(FirebaseActivity.CREATE_ACCOUNT_ACTION);
        startActivityForResult(intent, FirebaseActivity.CREATE_ACCOUNT);
    }

    @SuppressLint("NonConstantResourceId")
    @NotNull
    private String getSex() {
        int selected = chipGroupSex.getCheckedChipId();
        switch (selected) {
            case R.id.chipMale:
                return "M";
            case R.id.chipFemale:
                return "F";
            default:
                return "O";
        }
    }

    void setLogIn(View view) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .");
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgAvt.setImageURI(selectedImageUri);
                }
            } else if (requestCode == TAKE_PICTURE) {
                setPic(imgAvt);
            }
        } else if (resultCode == FirebaseActivity.CREATE_ACCOUNT_OK) {
            if (requestCode == FirebaseActivity.CREATE_ACCOUNT) {
                Toast.makeText(this, "Đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}