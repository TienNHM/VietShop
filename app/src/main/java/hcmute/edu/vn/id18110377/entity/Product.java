package hcmute.edu.vn.id18110377.entity;

public class Product {
    private int id;
    private String productName;
    private int mainImageID;
    private int imgSpecialID;

    public Product(int id, String productName, int mainImageID) {
        this.id = id;
        this.productName = productName;
        this.mainImageID = mainImageID;
    }

    public Product(int id, String productName, int mainImageID, int imgSpecialID) {
        this.imgSpecialID = imgSpecialID;
        new Product(id, productName, mainImageID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMainImageID() {
        return mainImageID;
    }

    public void setMainImageID(int mainImageID) {
        this.mainImageID = mainImageID;
    }

    public int getImgSpecialID() {
        return imgSpecialID;
    }

    public void setImgSpecialID(int imgSpecialID) {
        this.imgSpecialID = imgSpecialID;
    }
}
