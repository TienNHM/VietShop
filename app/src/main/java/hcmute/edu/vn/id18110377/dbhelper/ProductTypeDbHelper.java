package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.ProductType;

public class ProductTypeDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "ProductType";

    public ProductTypeDbHelper(@Nullable Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS ProductType ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    name    TEXT NOT NULL, " +
                        "    image    BLOB, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id) " +
                        ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public ArrayList<ProductType> getAllProductTypes() {
        ArrayList<ProductType> productTypes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productTypes.add(
                    new ProductType(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getBlob(2),
                            cursor.getString(3)
                    )
            );
            cursor.moveToNext();
        }
        cursor.close();

        return productTypes;
    }
}
