package hcmute.edu.vn.id18110377.entity;

import android.graphics.Bitmap;

public class Product {
    private Integer id;
    private Integer type;
    private String name;
    private Double price;
    private Bitmap image;
    private String detail;
    private Float star;
    private String status;

    public Product(Integer id, Integer type, String name, Double price, Bitmap image, String detail, Float star, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.image = image;
        this.detail = detail;
        this.star = star;
        this.status = status;
    }

    public Product(Integer id, Integer type, String name, Double price) {
        this(id, type, name, price, null, null, 0.0f, null);
    }

    public Product(Integer type, String name, Double price) {
        this(-1, type, name, price, null, null, 0.0f, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
