package hcmute.edu.vn.id18110377.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @BindView(R.id.txtComfirmPassword)
    TextInputEditText txtConfirmPassword;
    @BindView(R.id.layoutConfirmPassword)
    TextInputLayout layoutConfirmPassword;
    @BindView(R.id.imgAvt)
    ImageView imgAvt;
    @BindView(R.id.chipGroupSex)
    ChipGroup chipGroupSex;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int SELECT_PICTURE = 200;
    private static final int TAKE_PICTURE = 100;
    private String currentPhotoPath;

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
            if (checkPermission() == false)
                requestPermission();
        }

        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        findViewById(R.id.txtSignIn).setOnClickListener(this::setLogIn);
        findViewById(R.id.btnSignUp).setOnClickListener(this::setSignUp);
        findViewById(R.id.btnTakePhoto).setOnClickListener(this::setTakePhoto);
        findViewById(R.id.btnChoosePhoto).setOnClickListener(this::setChoosePhoto);
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
        return !confirmPassword.equals("");
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

        if (validate(fullName, email, phone, address, username, password, confirmPassword) == false) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin cá nhân!", Toast.LENGTH_SHORT).show();
            return;
        }

        Account account = new Account(username, email, AppUtilities.encode(password));
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
                createFirebaseUser(account.getEmail(), account.getPassword());
                Toast.makeText(this, "Đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
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

    private void setTakePhoto(View view) {
        try {
            dispatchTakePictureIntent(TAKE_PICTURE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setChoosePhoto(View view) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(SignUp.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(SignUp.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(SignUp.this,
                    "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(
                    SignUp.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
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
                setPic();
            }
        } else if (resultCode == FirebaseActivity.CREATE_ACCOUNT_OK) {
            if (requestCode == FirebaseActivity.CREATE_ACCOUNT) {
                Toast.makeText(this, "Đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void dispatchTakePictureIntent(int requestCode) throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "hcmute.edu.vn.id18110377",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, requestCode);
            }
        }
    }

    @NotNull
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",   /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imgAvt.getWidth();
        int targetH = imgAvt.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imgAvt.setImageBitmap(bitmap);
    }
}