package DAL;

import Models.Category;
import Models.Comment;
import Models.Like;
import Models.News;
import Models.Question;
import Models.Role;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Misaki
 */
public class DAO {

    private ArrayList<News> nList;
    private HashMap<Integer, Category> cateHm;
    private HashMap<Integer,User> userHm;
    private HashMap<Integer, Role> roleHm;
    private HashMap<Integer, Question> questHm;
    private ArrayList<Like> likeList;
    private ArrayList<Comment> cmtList;
    private String status;
    private Connection con;

    public ArrayList<News> getnList() {
        return nList;
    }

    public void setnList(ArrayList<News> nList) {
        this.nList = nList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<Integer, Category> getCateHm() {
        return cateHm;
    }

    public void setCateHm(HashMap<Integer, Category> cateHm) {
        this.cateHm = cateHm;
    }

    public HashMap<Integer, User> getUserHm() {
        return userHm;
    }

    public void setUserHm(HashMap<Integer, User> userHm) {
        this.userHm = userHm;
    }

    public HashMap<Integer, Role> getRoleHm() {
        return roleHm;
    }

    public void setRoleHm(HashMap<Integer, Role> roleHm) {
        this.roleHm = roleHm;
    }

    public HashMap<Integer, Question> getQuestHm() {
        return questHm;
    }

    public void setQuestHm(HashMap<Integer, Question> questHm) {
        this.questHm = questHm;
    }

    public ArrayList<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(ArrayList<Like> likeList) {
        this.likeList = likeList;
    }

    public ArrayList<Comment> getCmtList() {
        return cmtList;
    }

    public void setCmtList(ArrayList<Comment> cmtList) {
        this.cmtList = cmtList;
    }
    
    
    
    

    public DAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Error connection at NewsDAO " + e.getMessage();
        }
    }

    public void loadNew() {
        nList = new ArrayList<News>();
        String sql = "select * from News_he160797";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int cateId = rs.getInt(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String detail = rs.getString(5);
                String image = rs.getString(6);
                LocalDate date = rs.getDate(7).toLocalDate();
                int userId = rs.getInt(8);
                boolean active = rs.getBoolean(9);
                int likeNum = rs.getInt(10);
                int cmtNum = rs.getInt(11);
                nList.add(new News(id, cateId, name, description, detail, image, date, userId, active, likeNum, cmtNum));
            }

        } catch (Exception e) {
            status = "Error News " + e.getMessage();
        }
    }

    public void loadCate() {
        String sql = "select * from categories_he160797";
        cateHm = new HashMap<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                cateHm.put(id, new Category(id, name));
            }
        } catch (Exception e) {
            status = "Error Category loading" + e.getMessage();
        }
    }

    public void loadUser(){
        String sql = "select * from users_he160797";
        userHm = new HashMap<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                int roleId = rs.getInt(5);
                int quesId = rs.getInt(6);
                String answer = rs.getString(7);
                String email = rs.getString(8);
                boolean gender = rs.getBoolean(9);
                boolean active = rs.getBoolean(10);
                LocalDate dob = rs.getDate(11).toLocalDate();
                String address = rs.getString(12);
                String avatar = rs.getString(13);
                boolean verify = rs.getBoolean(14);
                userHm.put(id, new User(id,fullname,username,password,roleId,quesId,answer,email,gender,active,dob,address,avatar, verify));
                
            }
        } catch (Exception e) {
            status = "Error at LoadUser " + e.getMessage();
        }
    }
    
    public void loadRole(){
        String sql = "select * from roles_he160797";
        roleHm = new HashMap<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String role = rs.getString(2);
                roleHm.put(id, new Role(id,role));
            }
        } catch (Exception e) {
            status = "Error at loadRole " + e.getMessage();
        }
    }
    
    public void loadQuestion(){
        String sql = "select * from questions_he160797";
        questHm = new HashMap<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String question = rs.getString(2);
                questHm.put(id, new Question(id,question));
            }
        } catch (Exception e) {
            status = "Error at loadQuestion " + e.getMessage();
        }
    }
    
    public void loadComment(){
        String sql = "select * from comments_he160797";
        cmtList = new ArrayList<Comment>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                int newId = rs.getInt(3);
                String cmt = rs.getString(4);
                cmtList.add(new Comment(id, userId, newId, cmt));
            }
        } catch (Exception e) {
            status = "Error at loadComment " + e.getMessage();
        }
    }
    
    public void loadLike(){
        String sql = "select * from likes_he160797";
        likeList = new ArrayList<Like>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                int newId = rs.getInt(3);
                likeList.add(new Like(id, userId, newId));
            }
        } catch (Exception e) {
            status = "Error at loadLike " + e.getMessage();
        }
    }
    
    public void Insert(User newUser){
        String sql = "insert into users_he160797 values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newUser.getFullname());
            ps.setString(2, newUser.getUsername());
            ps.setString(3, newUser.getPassword());
            ps.setInt(4, newUser.getRoleId());
            ps.setInt(5, newUser.getQuesId());
            ps.setString(6, newUser.getAnswer());
            ps.setString(7, newUser.getEmail());
            ps.setBoolean(8, newUser.isGender());
            ps.setBoolean(9, newUser.isActive());
            
            //Xu ly Date
            java.sql.Date date = java.sql.Date.valueOf(newUser.getDob());
            ps.setDate(10, date);
            
            ps.setString(11, newUser.getAddress());
            ps.setString(12, newUser.getAvatar());
            ps.setBoolean(13, newUser.isVerify());
            System.out.println(1);
            ps.execute();
        } catch (Exception e) {
            status = "Error at Insert " + e.getMessage();
            System.out.println(status);
        }
    }
    
    public boolean ValidateUsername(String username){
        boolean check = true;
        Set<Integer> keySet = userHm.keySet();
        for(Integer key: keySet){
            if(userHm.get(key).getUsername().equals(username)){
                check = false;
                break;
            }
        }
        return check;
    }
    
    public boolean ValidateMail(String mail){
        boolean check = true;
        Set<Integer> keySet = userHm.keySet();
        for(Integer key: keySet){
            if(userHm.get(key).getEmail().equals(mail)){
                check = false;
                break;
            }
        }
        return check;
    }
    
    public User ValidateLogin(String username, String password){
        Set<Integer> keySet = userHm.keySet();
        for(Integer key: keySet){
            if(userHm.get(key).getUsername().equals(username) && userHm.get(key).getPassword().equals(password)){
                return userHm.get(key);
            }
        }
        return null;
    }
    
    public void update(User newUser){
        
        String sql = "update users_he160797 set fullname=?, username=?, password=?, role_id=?,"
                + "question_id=?, answer=?, email=?, gender=?, active=?, dob=?, address=?, avatar=?, verify=? where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,newUser.getFullname());
            ps.setString(2,newUser.getUsername());
            ps.setString(3,newUser.getPassword());
            ps.setInt(4,newUser.getRoleId());
            ps.setInt(5,newUser.getQuesId());
            ps.setString(6,newUser.getAnswer());
            ps.setString(7,newUser.getEmail());
            ps.setBoolean(8,newUser.isGender());
            ps.setBoolean(9,newUser.isActive());
            
            java.sql.Date date = java.sql.Date.valueOf(newUser.getDob());
            ps.setDate(10,date);
            
            ps.setString(11,newUser.getAddress());
            ps.setString(12,newUser.getAvatar());
            ps.setBoolean(13,newUser.isVerify());
            ps.setInt(14,newUser.getId());
            ps.execute();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void VerifyUser(String username){
        String sql = "update users_he160797 set verify=1 where username=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at VerifyUser " + e.getMessage();
        }
    }
    
    public void ChangePass(int id, String newPass){
        String sql = "update users_he160797 set password=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,newPass);
            ps.setInt(2,id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at ChangePass " + e.getMessage();
        }
    }
    
    public int getUserToResetPass(String username, int quesId, String answer){
        String sql = "select id from users_he160797 where username=? and question_id=? and answer=?";
        int id=-1;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setInt(2,quesId);
            ps.setString(3,answer);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        }catch(Exception e){
            status = "Error at ResetPass " + e.getMessage();
        }
        return id;
    }
    
    public void Insert(News newActicle){
        System.out.println(newActicle.getName());
        String sql = "insert into news_he160797 values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, newActicle.getCateId());
            ps.setString(2, newActicle.getName());
            ps.setString(3, newActicle.getDescription());
            ps.setString(4, newActicle.getDetail());
            ps.setString(5, newActicle.getImage());
            
            //Xu ly Date
            java.sql.Date date = java.sql.Date.valueOf(newActicle.getDate());
            ps.setDate(6, date);
            
            ps.setInt(7, newActicle.getUserId());
            ps.setBoolean(8, newActicle.isActive());
            ps.setInt(9, newActicle.getLikeNum());
            ps.setInt(10, newActicle.getCmtNum());
            ps.execute();
        } catch (Exception e) {
            status = "Error at Insert " + e.getMessage();
            System.out.println(status);
        }
        
    }
    
    public void Update(News newActicle){
        System.out.println("ArticleId: " + newActicle.getId());
        System.out.println("ArticleName: " + newActicle.getName());
        String sql = "update news_he160797 set category_id=?, name=?, description=?, detail=?,"
                + "image=?, date=?, user_id=?, active=?, like_number=?, comment_number=? where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,newActicle.getCateId());
            ps.setString(2,newActicle.getName());
            ps.setString(3,newActicle.getDescription());
            ps.setString(4,newActicle.getDetail());
            ps.setString(5,newActicle.getImage());
            
            
            java.sql.Date date = java.sql.Date.valueOf(newActicle.getDate());
            ps.setDate(6,date);
            
            ps.setInt(7,newActicle.getUserId());
            ps.setBoolean(8,newActicle.isActive());
            ps.setInt(9,newActicle.getLikeNum());
            ps.setInt(10, newActicle.getCmtNum());
            ps.setInt(11,newActicle.getId());
            ps.execute();
        } catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void Delete(int id){
        String sql = "delete from news_he160797 where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        }catch(Exception e)
        {
            status = "Error at delete " + e.getMessage();
        }
    }
    
    public void ArticleActive(int id, boolean active){
        String sql = "update news_he160797 set active=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,active);
            ps.setInt(2,id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at ArticleActive " + e.getMessage();
        }
    }
    
    public int getLikeNumber(int id){
        String sql = "select like_number from news_he160797 where id=?";
        int likeNum = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                likeNum = rs.getInt(1);
                return likeNum;
            }
        } catch (Exception e) {
        }
        return likeNum;
    }
    
    public void addLikeToArticle(int id){
        
        
        String sql = "update news_he160797 set like_number=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,getLikeNumber(id)+1);
            ps.setInt(2,id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at addLikeToArticle " + e.getMessage();
        }
    }
    
    public void subLikeToArticle(int id){
        
        String sql = "update news_he160797 set like_number=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,getLikeNumber(id)-1);
            ps.setInt(2,id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at subLikeToArticle " + e.getMessage();
        }
    }
    
    public int getCmtNumber(int id){
        String sql = "select comment_number from news_he160797 where id=?";
        int cmtNum = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cmtNum = rs.getInt(1);
                return cmtNum;
            }
        } catch (Exception e) {
        }
        return cmtNum;
    }
    
    public void addCmtToArticle(int id){
        
        
        String sql = "update news_he160797 set comment_number=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,getCmtNumber(id)+1);
            ps.setInt(2,id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at addCmtToArticle " + e.getMessage();
        }
    }
    
    public void subCmtToArticle(int id){
        
        
        String sql = "update news_he160797 set comment_number=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,getCmtNumber(id)-1);
            ps.setInt(2,id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at subCmtToArticle " + e.getMessage();
        }
    }
    
    public void deleteCate(int id){
        String sql = "delete from categories_he160797 where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        }catch(Exception e)
        {
            status = "Error at delete " + e.getMessage();
        }
    }
    
    public void insertCate(String name){
        String sql = "insert into categories_he160797 values(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
        } catch (Exception e) {
            status = "Error at Insert " + e.getMessage();
            System.out.println(status);
        }
    }
    
    public void updateCate(int id, String name){
        String sql = "update categories_he160797 set name=? where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,id);
            ps.execute();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void insertComment(int userId, int newId, String cmt){
        String sql = "insert into comments_he160797 values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, newId);
            ps.setString(3, cmt);
            ps.execute();
        } catch (Exception e) {
            status = "Error at insertComment " + e.getMessage();
        }
    }
    
    public void updateComment(int id, String newCmt){
        String sql = "update comments_he160797 set comment=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newCmt);
            ps.setInt(2, id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at updateComment " + e.getMessage();
        }
    }
    
    public void deleteComment(int id){
        String sql = "delete from comments_he160797 where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        }catch(Exception e)
        {
            status = "Error at deleteComment " + e.getMessage();
        }
    }
    
    public void insertLike(int userId, int newId){
        String sql = "insert into likes_he160797 values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, newId);
            ps.execute();
        } catch (Exception e) {
            status = "Error at insertLike " + e.getMessage();
        }
    }
    
    public void deleteLike(int userId, int newId){
        String sql = "delete from likes_he160797 where user_id=? and new_id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,newId);
            ps.execute();
        }catch(Exception e)
        {
            status = "Error at deleteLike " + e.getMessage();
        }
    }
    
    public void Like(int userId, int newId){
        insertLike(userId, newId);
        addLikeToArticle(newId);
    }
    
    public void UnLike(int userId, int newId){
        deleteLike(userId, newId);
        subLikeToArticle(newId);
    }
    
    public void Comment(int userId, int newId, String cmt){
        insertComment(userId, newId, cmt);
        addCmtToArticle(newId);
    }
    
    public void DeleComment(int id, int newId){
        deleteComment(id);
        subCmtToArticle(newId);
    }
    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.VerifyUser("harry");
    }
//    public static void main(String[] args) {
//        DAO dao;
//        dao = new DAO();
//        dao.DeleComment(13, 4);
//        
////        Set<Integer> keySet = dao.cateHm.keySet();
////        for(Integer key: keySet){
////            System.out.println(dao.cateHm.get(key));
////        }
//        
//    }
}





