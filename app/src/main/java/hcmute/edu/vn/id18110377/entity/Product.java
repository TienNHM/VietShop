package hcmute.edu.vn.id18110377.entity;

import android.graphics.Bitmap;

import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class Product {
    private Integer id;
    private Integer type;
    private String name;
    private Double price;
    private Bitmap image;
    private int defaultImage;
    private String detail;
    private Float star;
    private String status;

    public Product(Integer id, Integer type, String name, Double price,
                   Bitmap image, String detail, Float star, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.image = image;
        this.detail = detail;
        this.star = star;
        this.status = status;
    }

    public Product(Integer id, Integer type, String name, Double price,
                   int defaultImage, String detail, Float star, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.defaultImage = defaultImage;
        this.image = ImageConverter.resource2Bitmap(defaultImage);
        this.detail = detail;
        this.star = star;
        this.status = status;
    }

    public Product(Integer id, Integer type, String name, Double price) {
        this(id, type, name, price, null, null, 0.0f, null);
    }

    public Product(Integer type, String name, int defaultImage) {
        this(-1, type, name, 0.0, defaultImage, null, 0.0f, null);
    }

    public Product(Integer type, String name, Bitmap image) {
        this(-1, type, name, 0.0, image, null, 0.0f, null);
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
        if (type > 0 && type < 8)
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
        if (price >= 0)
            this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public byte[] getRawImage() {
        return ImageConverter.bitmap2Byte(this.getImage());
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
        if (star >= 0)
            this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
