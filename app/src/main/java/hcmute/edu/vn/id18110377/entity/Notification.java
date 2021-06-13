package hcmute.edu.vn.id18110377.entity;

public class Notification {

    private static final String NOTIFY_UNREAD = "Unread";
    private static final String NOTIFY_READ = "Read";
    private Integer id;
    private Integer userId;
    private String type;
    private String message;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Notification(Integer id, Integer userId, String type, String message, String status) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.status = status;
    }

    public Notification(Integer userId, String type, String message) {
        this(-1, userId, type, message, NOTIFY_UNREAD);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
