package hcmute.edu.vn.id18110377.entity;

import android.graphics.Bitmap;

public class User {
    private Integer id;
    private Integer accountID;
    private String fullname;
    private String sex;
    private String email;
    private String phone;
    private Bitmap avatar;
    private String facebook;
    private String zalo;
    private String status;

    public User(Integer id, Integer accountID, String fullname, String sex, String email,
                String phone, Bitmap avatar, String facebook, String zalo, String status) {
        this.id = id;
        this.accountID = accountID;
        this.fullname = fullname;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.facebook = facebook;
        this.zalo = zalo;
        this.status = status;
    }

    public User(Integer id, Integer accountID, String fullname, String sex, String email) {
        this(id, accountID, fullname, sex, email, null, null, null, null, null);
    }

    public User(Integer accountID, String fullname, String sex, String email) {
        this(-1, accountID, fullname, sex, email, null, null, null, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
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

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
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
}
