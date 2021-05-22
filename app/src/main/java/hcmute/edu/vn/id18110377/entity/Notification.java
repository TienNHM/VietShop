package hcmute.edu.vn.id18110377.entity;

public class Notification {
    private Integer id;
    private String shortDetail;
    private String detail;
    private String type;
    private String status;

    public Notification(Integer id, String type, String status, String detail, String shortDetail) {
        this.id = id;
        this.type = type;
        this.detail = detail;
        this.status = status;
        this.shortDetail = shortDetail;
    }

    public Notification(Integer id, String type, String status, String detail) {
        this(id, type, status, detail, null);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", shortDetail='" + shortDetail + '\'' +
                ", detail='" + detail + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
