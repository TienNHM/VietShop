package hcmute.edu.vn.id18110377.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.UserDbHelper;

import static hcmute.edu.vn.id18110377.utilities.AccountSesionManager.account;
import static hcmute.edu.vn.id18110377.utilities.AccountSesionManager.currentUser;
import static hcmute.edu.vn.id18110377.utilities.AccountSesionManager.mAuth;
import static hcmute.edu.vn.id18110377.utilities.AccountSesionManager.user;

public class FirebaseActivity extends Activity {

    public static final String TAG = "EmailPassword";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String SIGN_IN_ACTION = "Sign in";
    public static final String CREATE_ACCOUNT_ACTION = "Create account";
    public static final String VERIFY_ACTION = "Verify";
    public static final int SIGN_IN = 1;
    public static final int CREATE_ACCOUNT = 2;
    public static final int VERIFY = 3;
    public static final int SIGN_IN_OK = 100;
    public static final int CREATE_ACCOUNT_OK = 200;
    public static final int VERIFY_OK = 300;

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        Intent intent = getIntent();
        this.email = intent.getStringExtra(EMAIL);
        this.password = intent.getStringExtra(PASSWORD);
        action(Objects.requireNonNull(intent.getAction()));
    }

    private void action(@NotNull String action) {
        if (action.equals(SIGN_IN_ACTION)) {
            signIn();
        } else if (action.equals(CREATE_ACCOUNT_ACTION)) {
            createAccount();
            sendEmailVerification();
        } else if (action.equals(VERIFY_ACTION)) {
            sendEmailVerification();
        }
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }
    // [END on_start_check_user]

    private void createAccount() {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        currentUser = mAuth.getCurrentUser();
                        updateAccountSession();
                        setResult(CREATE_ACCOUNT_OK);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(FirebaseActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        setResult(-1);
                        finish();
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn() {
        // [START sign_in_with_email]
        // password has been encoded
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        updateAccountSession();
                        setResult(SIGN_IN_OK);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(FirebaseActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        setResult(-1);
                        finish();
                    }
                });
        // [END sign_in_with_email]
    }

    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        currentUser = (currentUser == null) ? mAuth.getCurrentUser() : currentUser;
        currentUser.sendEmailVerification()
                .addOnCompleteListener(this, task -> {
                    Log.i(EMAIL, "Email sent.");
                    setResult(VERIFY_OK);
                    finish();
                });
        // [END send_email_verification]
        setResult(-1);
        finish();
    }

    private void reload() {
    }

    private void updateAccountSession() {
        currentUser = (currentUser == null) ? mAuth.getCurrentUser() : currentUser;
        AccountDbHelper accountDbHelper = new AccountDbHelper(this);
        account = accountDbHelper.login(email, password);
        if (account != null) {
            UserDbHelper userDbHelper = new UserDbHelper(this);
            user = userDbHelper.getUserByAccountId(account.getId());
            if (user == null) {
                account = null;
                Toast.makeText(this, "Đã có lỗi phát sinh trong quá trình đăng nhập.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Đăng nhập thất bại. Vui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
        }
    }
}