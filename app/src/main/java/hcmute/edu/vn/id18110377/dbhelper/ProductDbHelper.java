package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProductDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Product";

    public ProductDbHelper(@Nullable Context context) {
        super(context, DbManager.DATABASE_NAME, null, DbManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Product ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    type    INTEGER NOT NULL, " +
                        "    name    TEXT NOT NULL, " +
                        "    price    REAL, " +
                        "    image    BLOB, " +
                        "    detail    TEXT, " +
                        "    star    REAL, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id), " +
                        "    FOREIGN KEY(type) REFERENCES ProductType(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
