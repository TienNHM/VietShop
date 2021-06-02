package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Store;
import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class StoreDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Store";
    private static final String STORE_NAME = "Store";

    public StoreDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Store (\n" +
                        "    id    INTEGER NOT NULL, " +
                        "    name    TEXT NOT NULL, " +
                        "    phone    TEXT NOT NULL, " +
                        "    email    TEXT NOT NULL, " +
                        "    image    BLOB, " +
                        "    address    TEXT, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id) " +
                        ")";
        db.execSQL(query);
    }

    private Store cursorToStore(Cursor cursor) {
        return new Store(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                ImageConverter.byte2Bitmap(cursor.getBlob(4)),
                cursor.getString(5),
                cursor.getString(6)
        );
    }

    public ArrayList<Store> getAllStores() {
        ArrayList<Store> stores = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stores.add(
                    cursorToStore(cursor)
            );
            cursor.moveToNext();
        }
        cursor.close();
        return stores;
    }

    public ArrayList<Store> getStoreByName(String name) {
        return getStoreByField(STORE_NAME, name);
    }

    private ArrayList<Store> getStoreByField(String field, String value) {
        ArrayList<Store> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + field + " LIKE %?%", new String[]{value});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            products.add(
                    cursorToStore(cursor)
            );
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }
}
