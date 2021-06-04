package hcmute.edu.vn.id18110377.entity;

import android.graphics.Bitmap;

import hcmute.edu.vn.id18110377.utilities.ImageConverter;

public class ProductType {
    private Integer id;
    private String name;
    private Bitmap image;
    private String status;

    public ProductType(Integer id, String name, Object image, String status) {
        this.setId(id);
        this.setName(name);
        this.setStatus(status);
        if (image instanceof Bitmap)
            this.setImage((Bitmap) image);
        else if (image instanceof byte[])
            this.setImage((byte[]) image);
        else
            this.setImage((int) image);
    }

    public ProductType(String name) {
        this.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImage(byte[] image) {
        this.image = ImageConverter.byte2Bitmap(image);
    }

    public void setImage(int image) {
        this.image = ImageConverter.resource2Bitmap(image);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
