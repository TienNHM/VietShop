package hcmute.edu.vn.id18110377.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import hcmute.edu.vn.id18110377.MainActivity;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class User {
    private static final int DEFAULT_AVT_FEMALE = R.drawable.female_profile;
    private static final int DEFAULT_AVT_MALE = R.drawable.male_profile;
    private static final int DEFAULT_AVT_UNDEFINED = R.drawable.cat_profile;

    private Integer id;
    private Integer accountId;
    private String fullname;
    private String sex;
    private String email;
    private String phone;
    private Bitmap avatar;
    private String facebook;
    private String zalo;
    private String status;

    public User(Integer id, Integer accountId, String fullname, String sex, String email,
                String phone, Bitmap avatar, String facebook, String zalo, String status) {
        this.id = id;
        this.accountId = accountId;
        this.fullname = fullname;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.facebook = facebook;
        this.zalo = zalo;
        this.status = status;
    }

    public User(Integer id, Integer accountId, String fullname, String email, String sex,
                String phone, String facebook, String zalo, String status) {
        this.id = id;
        this.accountId = accountId;
        this.fullname = fullname;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.setAvatar(sex);
        this.facebook = facebook;
        this.zalo = zalo;
        this.status = status;
    }

    public User(Integer id, Integer accountId, String fullname, String email, String sex) {
        this(id, accountId, fullname, email, sex, null, null, null, null);
    }

    public User(Integer accountId, String fullname, String email) {
        this(-1, accountId, fullname, email, null, null, null, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@"))
            this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public byte[] getRawAvatar() {
        return ImageConverter.bitmap2Byte(this.getAvatar());
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public void setAvatar(String sex) {
        int avatar = DEFAULT_AVT_UNDEFINED;
        if (sex != null) {
            if (sex.startsWith("F"))
                avatar = DEFAULT_AVT_FEMALE;
            else if (sex.startsWith("M"))
                avatar = DEFAULT_AVT_MALE;
        }
        this.avatar = BitmapFactory.decodeResource(MainActivity.mainResources, avatar);
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getZalo() {
        return zalo;
    }

    public void setZalo(String zalo) {
        this.zalo = zalo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
