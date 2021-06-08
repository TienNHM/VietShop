package hcmute.edu.vn.id18110377.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class CartDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Cart";
    private static final String CART_ID = "id";
    private static final String CART_USERID = "userId";
    private static final String CART_PRODUCTID = "productId";
    private static final String CART_QUANTITY = "quantity";
    private static final String CART_ADDRESS = "address";
    private static final String CART_STATUS = "status";
    private static final String CART_UNPAID = "Unpaid";

    public CartDbHelper(@Nullable Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
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

    private Cart cursorToCart(Cursor cursor) {
        return new Cart(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5)
        );
    }

    private ArrayList<Cart> getCart(Cursor cursor) {
        ArrayList<Cart> carts = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cart cart = cursorToCart(cursor);
            cart.setProduct(
                    new Product(
                            cursor.getInt(6),
                            cursor.getInt(7),
                            cursor.getInt(8),
                            cursor.getString(9),
                            cursor.getDouble(10),
                            ImageConverter.byte2Bitmap(cursor.getBlob(11)),
                            cursor.getString(12),
                            cursor.getFloat(13),
                            cursor.getString(14)
                    )
            );
            carts.add(cart);
            cursor.moveToNext();
        }
        cursor.close();
        return carts;
    }

    public ArrayList<Cart> getAllCarts(Integer userId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Cart INNER JOIN Product ON Cart.productId = Product.id WHERE Cart.userId = ?",
                new String[]{userId.toString()});
        return getCart(cursor);
    }

    private ArrayList<Cart> getCartByStatus(Integer userId, String status) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Cart INNER JOIN Product ON Cart.productId = Product.id WHERE Cart.userId = ? AND Cart.status LIKE ?",
                new String[]{userId.toString(), "%" + status + "%"});
        return getCart(cursor);
    }

    public ArrayList<Cart> getUnpaidCart(Integer userId) {
        return getCartByStatus(userId, CART_UNPAID);
    }
}
