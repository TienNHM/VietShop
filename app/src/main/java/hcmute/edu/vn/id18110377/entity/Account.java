package hcmute.edu.vn.id18110377.entity;

import android.content.Context;

import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;

public class Account {
    private Integer id;
    private Integer userId;
    private String username;
    private String password;
    private Integer roleID;
    private String status;

    public Account(Integer id, Integer userId, String username, String password, Integer roleID, String status) {
        this.setId(id);
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setRoleID(roleID);
        this.setStatus(status);
    }

    public Account(Integer userId, String username, String password) {
        this(-1, userId, username, password, 2, null);
    }

    /**
     * Dùng cho Login
     *
     * @param username
     * @param password
     */
    public Account(String username, String password) {
        this(-1, -1, username, password, 2, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        if (roleID > 0)
            this.roleID = roleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer login(Context context) {
        AccountDbHelper accountDbHelper = new AccountDbHelper(context);
        Account account = accountDbHelper.login(this.getUsername(), this.getPassword());
        return account == null ? -1 : account.getUserId();
    }
}
