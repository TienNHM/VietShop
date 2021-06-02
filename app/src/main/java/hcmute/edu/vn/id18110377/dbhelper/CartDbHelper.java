package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Cart;

public class CartDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Cart";

    public CartDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Cart ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    userId    INTEGER NOT NULL, " +
                        "    productId    INTEGER NOT NULL, " +
                        "    quantity    INTEGER NOT NULL, " +
                        "    address    TEXT NOT NULL, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id), " +
                        "    FOREIGN KEY(userId) REFERENCES User(id), " +
                        "    FOREIGN KEY(productId) REFERENCES Product(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public ArrayList<Cart> getAllCarts(Integer userId) {
        ArrayList<Cart> carts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE userId = ?", new String[]{userId.toString()});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            carts.add(
                    new Cart(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getString(4),
                            cursor.getString(5)
                    ));
            cursor.moveToNext();
        }
        cursor.close();
        return carts;
    }
}
