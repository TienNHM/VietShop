package hcmute.edu.vn.id18110377.entity;

import hcmute.edu.vn.id18110377.utilities.AppUtilities;

public class Bill {
    public static final String BILL_UNPAID = "Unpaid";
    public static final String BILL_PAID = "Paid";
    private Integer id;
    private Integer userId;
    private Integer cartId;
    private String date;
    private String status;
    private Cart cart;

    public Bill(Integer id, Integer userId, Integer cartId, String date, String status) {
        this.id = id;
        this.userId = userId;
        this.cartId = cartId;
        this.date = date;
        this.status = status;
    }

    public Bill(Integer id, Integer userId, Integer cartId) {
        this(id, userId, cartId, AppUtilities.getDateTimeNow(), BILL_UNPAID);
    }

    public Bill(Integer userId, Integer cartId) {
        this(-1, userId, cartId, AppUtilities.getDateTimeNow(), BILL_UNPAID);
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
