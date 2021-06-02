package hcmute.edu.vn.id18110377.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.entity.Notification;

public class NotificationDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Notification";

    public NotificationDbHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE Notification ( " +
                        "    id    INTEGER NOT NULL, " +
                        "    type    TEXT NOT NULL, " +
                        "    detail    TEXT, " +
                        "    shortDetail    TEXT, " +
                        "    status    TEXT, " +
                        "    PRIMARY KEY(id) " +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public ArrayList<Notification> getAllNotifications(Integer userId) {
        ArrayList<Notification> notifications = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE userId = ?", new String[]{userId.toString()});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            notifications.add(
                    new Notification(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    )
            );
            cursor.moveToNext();
        }
        cursor.close();
        return notifications;
    }
}
