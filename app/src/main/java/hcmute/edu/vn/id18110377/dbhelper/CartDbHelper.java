package hcmute.edu.vn.id18110377.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Cart;

public class CartDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Cart";
    private static final String CART_ID = "id";
    private static final String CART_USERID = "userId";
    private static final String CART_PRODUCTID = "productId";
    private static final String CART_QUANTITY = "quantity";
    private static final String CART_ADDRESS = "address";
    private static final String CART_STATUS = "status";

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

    private ContentValues createContentValues(Cart cart) {
        ContentValues values = new ContentValues();
        values.put(CART_USERID, cart.getUserId());
        values.put(CART_PRODUCTID, cart.getProductId());
        values.put(CART_QUANTITY, cart.getQuantity());
        values.put(CART_ADDRESS, cart.getAddress());
        values.put(CART_STATUS, cart.getStatus());
        return values;
    }

    public long insert(Cart cart) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(cart);
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Cart cart) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(cart);
        return db.update(TABLE_NAME, values, CART_ID + " = ?", new String[]{String.valueOf(cart.getId())});
    }

    public int delete(Cart cart) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, CART_ID + " = ?", new String[]{String.valueOf(cart.getId())});
    }
}
