package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Promo;

public class PromoDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "Promo";

    public PromoDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
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

    private Promo cursorToPromo(Cursor cursor) {
        return new Promo(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );
    }

    public ArrayList<Promo> getAllPromos() {
        ArrayList<Promo> promos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            promos.add(
                    cursorToPromo(cursor)
            );
            cursor.moveToNext();
        }
        cursor.close();
        return promos;
    }

    public ArrayList<Promo> getTopPromos(int limit) {
        ArrayList<Promo> promos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " LIMIT ?", new String[]{String.valueOf(limit)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            promos.add(
                    cursorToPromo(cursor)
            );
            cursor.moveToNext();
        }
        cursor.close();
        return promos;
    }
}
