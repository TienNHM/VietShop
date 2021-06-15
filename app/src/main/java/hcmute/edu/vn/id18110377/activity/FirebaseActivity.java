package hcmute.edu.vn.id18110377.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

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
        action(intent.getAction());
    }

    private void action(@NotNull String action) {
        if (action.equals(SIGN_IN_ACTION)) {
            setResult(signIn());
        } else if (action.equals(CREATE_ACCOUNT_ACTION)) {
            createAccount();
            setResult(sendEmailVerification());
        } else if (action.equals(VERIFY_ACTION)) {
            setResult(sendEmailVerification());
        }
        finish();
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

    private int createAccount() {
        // [START create_user_with_email]
        final int[] result = {-1};
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                            updateAccountSession();
                            result[0] = CREATE_ACCOUNT_OK;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END create_user_with_email]
        return result[0];
    }

    private int signIn() {
        final int[] result = {-1};
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            updateAccountSession();
                            result[0] = SIGN_IN_OK;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
        return result[0];
    }

    private int sendEmailVerification() {
        // Send verification email
        final int[] result = {-1};
        // [START send_email_verification]
        currentUser = (currentUser == null) ? mAuth.getCurrentUser() : currentUser;
        currentUser.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i(EMAIL, "Email sent.");
                        result[0] = VERIFY_OK;
                    }
                });
        // [END send_email_verification]
        return result[0];
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