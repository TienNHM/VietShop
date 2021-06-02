package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hcmute.edu.vn.id18110377.entity.User;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "User";

    public UserDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE User ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    fullname    TEXT NOT NULL, " +
                        "    sex    TEXT NOT NULL, " +
                        "    email    TEXT NOT NULL, " +
                        "    phone    TEXT, " +
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

    public User getUser(Integer id) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", new String[]{id.toString()});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    DbHelper.convertToBitmap(cursor.getBlob(6)),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9)
            );
        }
        cursor.close();
        return user;
    }
}
