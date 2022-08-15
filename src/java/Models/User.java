package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class User {
    
    private int id;
    private String fullname, username, password;
    private int roleId, quesId;
    private String answer, email;
    private boolean gender, active;
    private LocalDate dob;
    private String address, avatar;
    private boolean verify;

    public User() {
    }

    public User(int id, String fullname, String username, String password, int roleId, int quesId, String answer, String email, boolean gender, boolean active, LocalDate dob, String address, String avatar, boolean verify) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.quesId = quesId;
        this.answer = answer;
        this.email = email;
        this.gender = gender;
        this.active = active;
        this.dob = dob;
        this.address = address;
        this.avatar = avatar;
        this.verify = verify;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
    
    
    
}
