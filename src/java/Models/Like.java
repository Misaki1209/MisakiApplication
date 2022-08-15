package Models;

/**
 *
 * @author Misaki
 */
public class Like {
    private int id;
    private int userId;
    private int newId;

    public Like() {
    }

    public Like(int id, int userId, int newId) {
        this.id = id;
        this.userId = userId;
        this.newId = newId;
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
    
    
}
