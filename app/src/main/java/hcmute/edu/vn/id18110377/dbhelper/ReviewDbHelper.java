package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ReviewDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Review";

    public ReviewDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Review ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    userID    INTEGER NOT NULL, " +
                        "    productID    INTEGER NOT NULL, " +
                        "    content    TEXT, " +
                        "    time    TEXT, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id), " +
                        "    FOREIGN KEY(userID) REFERENCES User(id), " +
                        "    FOREIGN KEY(productID) REFERENCES Product(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
