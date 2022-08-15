package Models;

import java.time.LocalDate;

/**
 *
 * @author Misaki
 */
public class News {
    private int id;
    private int cateId;
    private String name, description, detail, image;
    private LocalDate date;
    private int userId;
    private boolean active;
    private int likeNum, cmtNum;

    public News() {
    }

    public News(int id, int cateId, String name, String description, String detail, String image, LocalDate date, int userId, boolean active, int likeNum, int cmtNum) {
        this.id = id;
        this.cateId = cateId;
        this.name = name;
        this.description = description;
        this.detail = detail;
        this.image = image;
        this.date = date;
        this.userId = userId;
        this.active = active;
        this.likeNum = likeNum;
        this.cmtNum = cmtNum;
    }

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCmtNum() {
        return cmtNum;
    }

    public void setCmtNum(int cmtNum) {
        this.cmtNum = cmtNum;
    }
    
    
    
    
}
