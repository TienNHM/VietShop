package hcmute.edu.vn.id18110377.entity;

public class Bill {
    private Integer id;
    private Integer userId;
    private Integer cartId;
    private Double total;
    private String date;
    private String status;

    public Bill(Integer id, Integer userId, Integer cartId, Double total, String date, String status) {
        this.id = id;
        this.userId = userId;
        this.cartId = cartId;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public Bill(Integer id, Integer userId, Integer cartId, Double total) {
        this(id, userId, cartId, total, null, null);
    }

    public Bill(Integer userId, Integer cartId, Double total) {
        this(-1, userId, cartId, total, null, null);
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        if (total >= 0)
            this.total = total;
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
}
