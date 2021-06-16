package hcmute.edu.vn.id18110377.entity;

import hcmute.edu.vn.id18110377.utilities.AccountSessionManager;
import hcmute.edu.vn.id18110377.utilities.AppUtilities;

public class Bill {
    public static final String BILL_UNPAID = "Unpaid";
    public static final String BILL_PAID = "Paid";
    private Integer id;
    private Integer userId;
    private Integer cartId;
    private String address;
    private String date;
    private String status;
    private Cart cart;

    public Bill(Integer id, Integer userId, Integer cartId, String address, String date, String status) {
        this.setId(id);
        this.setUserId(userId);
        this.setCartId(cartId);
        this.setAddress(address);
        this.setDate(date);
        this.setStatus(status);
    }

    public Bill(Integer userId, Integer cartId, String address) {
        this(-1, userId, cartId, address, AppUtilities.getDateTimeNow(), BILL_UNPAID);
    }

    public Bill(Integer userId, Integer cartId) {
        this(-1, userId, cartId, AccountSessionManager.user.getAddress(), AppUtilities.getDateTimeNow(), BILL_UNPAID);
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

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
