package hcmute.edu.vn.id18110377.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.Base64;

import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;

public class AppUtilities {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public static String encode(@NotNull String plainString) {
        return Base64.getEncoder().withoutPadding().encodeToString(plainString.getBytes());
    }

    @NotNull
    public static String decode(String encodedString) {
        if (null == encodedString)
            return null;
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    public static void saveSession(@NotNull Context context, String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, AppUtilities.encode(username));
        editor.putString(PASSWORD, AppUtilities.encode(password));
        editor.commit();
    }

    public static Account getSession(@NotNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME, null);
        String password = sharedPreferences.getString(PASSWORD, null);
        if (username == null || password == null)
            return null;
        AccountDbHelper accountDbHelper = new AccountDbHelper(context);
        return accountDbHelper.login(decode(username), password);
    }
}