package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "User";

    public UserDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE User ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    accountID    INTEGER NOT NULL, " +
                        "    fullname    TEXT NOT NULL, " +
                        "    sex    TEXT NOT NULL, " +
                        "    email    TEXT NOT NULL, " +
                        "    phone    TEXT, " +
                        "    avatar    BLOB, " +
                        "    facebook    TEXT, " +
                        "    zalo    TEXT, " +
                        "    status    TEXT, " +
                        "    FOREIGN KEY(accountID) REFERENCES Account(id), " +
                        "    PRIMARY KEY(id)" +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
