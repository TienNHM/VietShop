package hcmute.edu.vn.id18110377.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.id18110377.MainActivity;

public class ImageConverter {
    public static Bitmap byte2Bitmap(byte[] img) {
        if (img == null)
            return null;
        return BitmapFactory.decodeByteArray(img, 0, img.length);
    }

    public static byte[] bitmap2Byte(Bitmap bitmap) {
        if (bitmap == null) return null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap resource2Bitmap(int resourceId) {
        return BitmapFactory.decodeResource(MainActivity.mainResources, resourceId);
    }
}
