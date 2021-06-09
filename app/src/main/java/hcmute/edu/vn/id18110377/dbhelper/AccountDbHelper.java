package hcmute.edu.vn.id18110377.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hcmute.edu.vn.id18110377.entity.Account;

public class AccountDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Account";
    private static final String ACCOUNT_ID = "id";
    private static final String ACCOUNT_USERNAME = "username";
    private static final String ACCOUNT_PASSWORD = "password";
    private static final String ACCOUNT_ROLEID = "roleId";
    private static final String ACCOUNT_STATUS = "status";

    public AccountDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Account ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    userId    INTEGER NOT NULL, " +
                        "    username    TEXT NOT NULL, " +
                        "    password    TEXT NOT NULL, " +
                        "    roleId    INTEGER NOT NULL, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    private Account cursorToAccount(Cursor cursor) {
        return new Account(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4)
        );
    }

    public Account getAccount(Integer id) {
        Account account = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", new String[]{id.toString()});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            account = cursorToAccount(cursor);
        }
        cursor.close();
        return account;
    }

    public Account login(String username, String password) {
        Account account = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?",
                new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            account = cursorToAccount(cursor);
        }
        cursor.close();
        return account;
    }

    private ContentValues createContentValues(Account account) {
        ContentValues values = new ContentValues();
        values.put(ACCOUNT_USERNAME, account.getUsername());
        values.put(ACCOUNT_PASSWORD, account.getPassword());
        values.put(ACCOUNT_ROLEID, account.getRoleID());
        values.put(ACCOUNT_STATUS, account.getStatus());
        return values;
    }

    public long insert(Account account) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(account);
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Account account) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(account);
        return db.update(TABLE_NAME, values, ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    public int delete(Account account) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, ACCOUNT_ID + " = ?", new String[]{String.valueOf(account.getId())});
    }

    public Account getAccountByRowId(long rowID) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE rowid = ?",
                new String[]{String.valueOf(rowID)});
        Account account = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            account = cursorToAccount(cursor);
        }
        cursor.close();
        return account;
    }

    public Integer getAccountIdByRowId(long rowId) {
        return getAccountByRowId(rowId).getId();
    }
}
