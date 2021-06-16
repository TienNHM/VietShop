package hcmute.edu.vn.id18110377.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;

import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;

public class AppUtilities {
    private static final String PREFERENCES = "hcmute.edu.vn.id18110377";
    private static final String EMAIL = "email";
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

    public static void saveSession(@NotNull Context context, String email, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, AppUtilities.encode(email));
        editor.putString(PASSWORD, AppUtilities.encode(password));
        editor.commit();
    }

    @Nullable
    public static Account getSession(@NotNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(EMAIL, "");
        String password = sharedPreferences.getString(PASSWORD, "");
        if (email.equals("") || password.equals(""))
            return null;
        AccountDbHelper accountDbHelper = new AccountDbHelper(context);
        return accountDbHelper.login(decode(email), password);
    }

    public static void clearSession(@NotNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(EMAIL);
        editor.remove(PASSWORD);
        editor.commit();
    }

    @NotNull
    public static String getDateTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static LocalDateTime stringToTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    @NotNull
    @Contract(" -> new")
    public static String generatePassword() {
        byte[] array = new byte[5]; // length is bounded by 7
        new Random().nextBytes(array);
        String str1 = new String(array, StandardCharsets.UTF_8);
        String str2 = decode(getDateTimeNow());
        return str1 + str2;
    }
}
