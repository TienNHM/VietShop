package hcmute.edu.vn.id18110377.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Bill;

public class BillDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Bill";
    private static final String BILL_ID = "id";
    private static final String BILL_USER_ID = "userId";
    private static final String BILL_ADDRESS = "address";
    private static final String BILL_CART_ID = "cartId";
    private static final String BILL_DATE = "date";
    private static final String BILL_STATUS = "status";

    public BillDbHelper(@Nullable Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS Bill ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    userId    INTEGER NOT NULL, " +
                        "    cartId    INTEGER NOT NULL, " +
                        "    address    TEXT NOT NULL, " +
                        "    date    TEXT, " +
                        "    status    TEXT, " +
                        "    FOREIGN KEY(cartId) REFERENCES Cart(id), " +
                        "    FOREIGN KEY(userId) REFERENCES User(id), " +
                        "    PRIMARY KEY(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    private Bill cursorToBill(Cursor cursor) {
        return new Bill(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );
    }

    private ContentValues createContentValues(Bill bill) {
        ContentValues values = new ContentValues();
        values.put(BILL_USER_ID, bill.getUserId());
        values.put(BILL_CART_ID, bill.getCartId());
        values.put(BILL_ADDRESS, bill.getAddress());
        values.put(BILL_DATE, bill.getDate());
        values.put(BILL_STATUS, bill.getStatus());
        return values;
    }

    public long insert(Bill bill) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(bill);
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Bill bill) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(bill);
        return db.update(
                TABLE_NAME, values, BILL_ID + " = ?",
                new String[]{String.valueOf(bill.getId())});
    }

    public int delete(Bill bill) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, BILL_ID + " = ?",
                new String[]{String.valueOf(bill.getId())});
    }

    public ArrayList<Bill> getAllBills(Integer userId) {
        ArrayList<Bill> bills = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + BILL_USER_ID + " = ?",
                new String[]{String.valueOf(userId)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            bills.add(cursorToBill(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return bills;
    }

    public ArrayList<Bill> getUnpaidBills(Integer userId) {
        ArrayList<Bill> bills = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + BILL_USER_ID + " = ? AND " + BILL_STATUS + " = " + Bill.BILL_UNPAID,
                new String[]{String.valueOf(userId)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            bills.add(cursorToBill(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return bills;
    }
}
