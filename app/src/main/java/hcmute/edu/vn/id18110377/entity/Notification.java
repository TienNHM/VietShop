package hcmute.edu.vn.id18110377.entity;

public class Notification {
    private Integer id;
    private String type;
    private String detail;
    private String title;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Notification(Integer id, String type, String title, String detail, String status) {
        this.id = id;
        this.type = type;
        this.detail = detail;
        this.status = status;
        this.title = title;
    }

    public Notification(Integer id, String type, String title) {
        this(id, type, title, null, null);
    }

    public Notification(String type, String title) {
        this(-1, type, title, null, null);
    }

    public String getDetail() {
        return detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
