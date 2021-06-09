package hcmute.edu.vn.id18110377.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hcmute.edu.vn.id18110377.entity.User;
import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "User";
    private static final String USER_ID = "id";
    private static final String USER_ACCOUNTID = "accountId";
    private static final String USER_FULLNAME = "fullname";
    private static final String USER_SEX = "sex";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE = "phone";
    private static final String USER_AVATAR = "avatar";
    private static final String USER_FACEBOOK = "facebook";
    private static final String USER_ZALO = "zalo";
    private static final String USER_STATUS = "status";

    public UserDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE User ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    fullname    TEXT NOT NULL, " +
                        "    email    TEXT NOT NULL, " +
                        "    sex    TEXT NOT NULL, " +
                        "    phone    TEXT NOT NULL, " +
                        "    avatar    BLOB, " +
                        "    facebook    TEXT, " +
                        "    zalo    TEXT, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id)" +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    private User cursorToUser(Cursor cursor) {
        return new User(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                ImageConverter.byte2Bitmap(cursor.getBlob(6)),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9)
        );
    }

    public User getUser(Integer id) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", new String[]{id.toString()});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = cursorToUser(cursor);
        }
        cursor.close();
        return user;
    }

    private ContentValues createContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(USER_FULLNAME, user.getFullname());
        values.put(USER_SEX, user.getSex());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PHONE, user.getPhone());
        values.put(USER_AVATAR, user.getRawAvatar());
        values.put(USER_FACEBOOK, user.getFacebook());
        values.put(USER_ZALO, user.getZalo());
        values.put(USER_STATUS, user.getStatus());
        return values;
    }

    public long insert(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(user);
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(user);
        return db.update(TABLE_NAME, values, USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public int delete(User user) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public User getUserByAccountId(Integer accountId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ACCOUNTID + " = ?",
                new String[]{String.valueOf(accountId)});
        User user = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = cursorToUser(cursor);
        }
        cursor.close();
        return user;
    }
}
