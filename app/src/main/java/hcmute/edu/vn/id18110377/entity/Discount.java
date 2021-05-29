package hcmute.edu.vn.id18110377.entity;

public class Discount {
    private Integer id;
    private Integer productId;
    private String expirationDate;
    private String type;
    private String status;

    public Discount(Integer id, Integer productId, String expirationDate, String type, String status) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public Discount(Integer id, Integer productId, String expirationDate) {
        this(id, productId, expirationDate, null, null);
    }

    public Discount(Integer productId, String expirationDate) {
        this(-1, productId, expirationDate, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
