package hcmute.edu.vn.id18110377.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.UserDbHelper;
import hcmute.edu.vn.id18110377.utilities.AppUtilities;
import hcmute.edu.vn.id18110377.utilities.ImageConverter;

import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.CHANGE_EMAIL;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.CHANGE_EMAIL_ACTION;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.CHANGE_EMAIL_OK;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.CREATE_ACCOUNT;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.CREATE_ACCOUNT_OK;
import static hcmute.edu.vn.id18110377.activity.FirebaseActivity.EMAIL;
import static hcmute.edu.vn.id18110377.utilities.AccountSessionManager.account;
import static hcmute.edu.vn.id18110377.utilities.AccountSessionManager.user;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.PERMISSION_REQUEST_CODE;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.SELECT_PICTURE;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.TAKE_PICTURE;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.requestPermission;
import static hcmute.edu.vn.id18110377.utilities.AppUtilities.setPic;

public class AccountInfoActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.imgAvt)
    ImageView imgAvt;
    @BindView(R.id.btnTakePhoto)
    Button btnTakePhoto;
    @BindView(R.id.btnChoosePhoto)
    Button btnChoosePhoto;
    @BindView(R.id.txtFullName)
    TextInputEditText txtFullName;
    @BindView(R.id.txtAddress)
    TextInputEditText txtAddress;
    @BindView(R.id.txtEmail)
    TextInputEditText txtEmail;
    @BindView(R.id.txtPhone)
    TextInputEditText txtPhone;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 23) {
            if (AppUtilities.checkPermission(this) == false)
                requestPermission(this);
        }

        btnBack.setOnClickListener(view -> finish());
        btnTakePhoto.setOnClickListener(AppUtilities::setTakePhoto);
        btnChoosePhoto.setOnClickListener(AppUtilities::setChoosePhoto);
        btnUpdate.setOnClickListener(this::setUpdate);
        setInfo();
        setAvatar();
    }

    private void setInfo() {
        if (user != null) {
            txtFullName.setText(user.getFullname());
            txtAddress.setText(user.getAddress());
            txtPhone.setText(user.getPhone());
            txtEmail.setText(account.getEmail());
        }
    }

    private void setAvatar() {
        if (user != null) {
            imgAvt.setImageBitmap(user.getAvatar());
        }
    }

    private void setUpdate(View view) {
        String fullName = txtFullName.getText().toString();
        String address = txtAddress.getText().toString();
        String phone = txtPhone.getText().toString();
        String email = txtEmail.getText().toString();

        if (account != null && user != null) {
            updateUserInfo(fullName, address, phone);
            updateEmail(email);
            finish();
        }
    }

    private void updateUserInfo(String fullName, String address, String phone) {
        user.setFullname(fullName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setAvatar(ImageConverter.drawable2Bitmap(imgAvt.getDrawable()));
        UserDbHelper userDbHelper = new UserDbHelper(this);
        int re = userDbHelper.update(user);
        if (re > 0) {
            Log.i("===Update user info to database===", "Success");
        }
    }

    private void updateEmail(String email) {
        account.setEmail(email);
        AccountDbHelper accountDbHelper = new AccountDbHelper(this);
        int re = accountDbHelper.update(account);
        if (re > 0) {
            Log.i("===Update account email to database===", "Success");
        }

        Intent intent = new Intent(this, FirebaseActivity.class);
        intent.putExtra(EMAIL, email);
        intent.setAction(CHANGE_EMAIL_ACTION);
        startActivityForResult(intent, CHANGE_EMAIL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    @Override
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
        } else if (resultCode == CREATE_ACCOUNT_OK) {
            if (requestCode == CREATE_ACCOUNT) {
                Toast.makeText(this, "Đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (resultCode == CHANGE_EMAIL_OK) {
            if (requestCode == CHANGE_EMAIL) {
                Toast.makeText(this, "Đã cập nhật thông tin thành công.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}