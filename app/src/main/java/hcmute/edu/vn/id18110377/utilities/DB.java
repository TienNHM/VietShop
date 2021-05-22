package hcmute.edu.vn.id18110377.utilities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.id18110377.entity.Product;

public class DB {

    /**
     * Kiểm tra Table đã tồn tại hay chưa
     *
     * @param db
     * @param table
     * @return
     */
    public static boolean isTableExist(SQLiteDatabase db, String table) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{table});
        boolean tableExist = (cursor.getCount() != 0);
        cursor.close();
        return tableExist;
    }

    private List<Product> loadDbProduct(Activity activity, String DB_Name) {
        List<Product> listProduct = new ArrayList<>();

        SQLiteDatabase db = activity.openOrCreateDatabase(DB_Name, Context.MODE_PRIVATE, null);

        if (!isTableExist(db, "product")) {
            return null;
        }

        Cursor cursor = db.rawQuery("SELECT id, name, price from product", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            //Đọc dữ liệu dòng hiện tại
            int productID = cursor.getInt(0);
            String productName = cursor.getString(1);
            int productPrice = cursor.getInt(2);

            listProduct.add(new Product(productID, productName, productPrice));

            // Đến dòng tiếp theo
            cursor.moveToNext();
        }

        cursor.close();

        return listProduct;
    }
}
