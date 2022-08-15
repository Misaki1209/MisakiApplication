package Models;

/**
 *
 * @author Misaki
 */
public class Comment {
    private int id, userId, newId;
    private String cmt;

    public Comment() {
    }

    public Comment(int id, int userId, int newId, String cmt) {
        this.id = id;
        this.userId = userId;
        this.newId = newId;
        this.cmt = cmt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
    
    
}
