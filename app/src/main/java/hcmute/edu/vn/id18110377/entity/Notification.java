package hcmute.edu.vn.id18110377.entity;

public class Notification {
    private Integer id;
    private String shortDetail;
    private String detail;
    private String type;

    public Notification(Integer id, String type, String detail) {
        this.id = id;
        this.type = type;
        this.detail = detail;
    }

    public Notification(Integer id, String type, String detail, String shortDetail) {
        this.shortDetail = shortDetail;
        new Notification(id, type, detail);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDetail() {
        return shortDetail;
    }

    public void setShortDetail(String shortDetail) {
        this.shortDetail = shortDetail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
