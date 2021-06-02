package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Product;

public class ProductDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Product";

    public ProductDbHelper(@Nullable Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
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

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            products.add(
                    new Product(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getDouble(3),
                            DbHelper.convertToBitmap(cursor.getBlob(4)),
                            cursor.getString(5),
                            cursor.getFloat(6),
                            cursor.getString(7))
            );
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }
}
