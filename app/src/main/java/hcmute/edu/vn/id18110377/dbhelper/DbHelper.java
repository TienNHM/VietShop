package hcmute.edu.vn.id18110377.dbhelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DbHelper {
    public static final String DATABASE_NAME = "vietshop.db";
    public static Integer DATABASE_VERSION = 1;

    public static void setDatabaseVersion(Integer databaseVersion) {
        if (databaseVersion > DATABASE_VERSION)
            DATABASE_VERSION = databaseVersion;
    }

    public static void upgradeDatabaseVersion() {
        DATABASE_VERSION += 1;
    }

    public static Bitmap convertToBitmap(byte[] img) {
        return BitmapFactory.decodeByteArray(img, 0, img.length);
    }
}
