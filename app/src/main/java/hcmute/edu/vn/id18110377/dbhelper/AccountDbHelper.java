package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Account";

    public AccountDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Account ( " +
                        "    id    INTEGER NOT NULL, " +
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
}
