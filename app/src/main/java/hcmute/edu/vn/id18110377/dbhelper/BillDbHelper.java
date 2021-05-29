package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BillDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Bill";

    public BillDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Bill ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    userId    INTEGER NOT NULL, " +
                        "    cartId    INTEGER NOT NULL, " +
                        "    total    REAL NOT NULL, " +
                        "    date    TEXT NOT NULL, " +
                        "    status    TEXT, " +
                        "    FOREIGN KEY(cartId) REFERENCES Cart(id), " +
                        "    FOREIGN KEY(userId) REFERENCES User(id), " +
                        "    PRIMARY KEY(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
