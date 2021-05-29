package hcmute.edu.vn.id18110377.entity;

public class Cart {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private String address;
    private String status;

    public Cart(Integer id, Integer userId, Integer productId, Integer quantity, String address, String status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.address = address;
        this.status = status;
    }

    public Cart(Integer id, Integer userId, Integer productId, Integer quantity, String address) {
        this(id, userId, productId, quantity, address, null);
    }

    public Cart(Integer userId, Integer productId, Integer quantity, String address) {
        this(-1, userId, productId, quantity, address, null);
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
