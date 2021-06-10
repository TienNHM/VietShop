package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class ProductImageDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "ProductImage";

    public ProductImageDbHelper(@Nullable Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS ProductImage ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    productId    INTEGER NOT NULL, " +
                        "    image    BLOB, " +
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

    public ArrayList<Bitmap> getAllImageByProduct(Integer productId) {
        ArrayList<Bitmap> images = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE productId = ?", new String[]{productId.toString()});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            images.add(ImageConverter.byte2Bitmap(cursor.getBlob(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return images;
    }
}
