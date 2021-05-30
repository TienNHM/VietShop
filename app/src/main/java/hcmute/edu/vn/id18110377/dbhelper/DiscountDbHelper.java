package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DiscountDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Discount";

    public DiscountDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Discount ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    productId    INTEGER NOT NULL, " +
                        "    expirationDate    TEXT, " +
                        "    type    TEXT, " +
                        "    status    TEXT, " +
                        "    FOREIGN KEY(productId) REFERENCES Product(id), " +
                        "    PRIMARY KEY(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
