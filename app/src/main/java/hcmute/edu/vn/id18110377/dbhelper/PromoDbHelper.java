package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PromoDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Promo";

    public PromoDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Promo ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    productId    INTEGER NOT NULL, " +
                        "    type    TEXT NOT NULL, " +
                        "    expirationDate    TEXT NOT NULL, " +
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
