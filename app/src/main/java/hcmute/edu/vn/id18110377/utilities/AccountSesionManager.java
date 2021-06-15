package hcmute.edu.vn.id18110377.utilities;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.UserDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;
import hcmute.edu.vn.id18110377.entity.User;

public class AccountSesionManager {
    public static FirebaseUser currentUser;
    public static FirebaseAuth mAuth;
    public static Account account;
    public static User user;

    public static boolean checkLogin(Context context) {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            AccountDbHelper accountDbHelper = new AccountDbHelper(context);
            account = accountDbHelper.getAccountByEmail(email);

            if (account == null) {
                currentUser = null;
                user = null;
                return false;
            }
            UserDbHelper userDbHelper = new UserDbHelper(context);
            user = userDbHelper.getUserByAccountId(account.getId());

            if (user == null) {
                account = null;
                //AppUtilities.clearSession(context);
                return false;
            } else {
                Toast.makeText(context, "Đã đăng nhập với tên " + user.getFullname(), Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public static void logout() {
        FirebaseAuth.getInstance().signOut();
        account = null;
        user = null;
    }

    public static boolean isEmailVerified() {
        if (mAuth != null && currentUser != null) {
            return currentUser.isEmailVerified();
        }
        return false;
    }
}
