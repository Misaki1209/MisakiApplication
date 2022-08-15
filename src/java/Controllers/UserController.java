package Controllers;

import DAL.DAO;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Misaki
 */
public class UserController extends HttpServlet {
    private DAO dao;
    private boolean check = false;
   
    public void init(){
        dao = new DAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String UserType = request.getParameter("UserType");
        String idd = request.getParameter("userId");
        int id;
        
        dao.loadRole();
        dao.loadQuestion();
        dao.loadUser();
        dao.loadCate();
        dao.loadNew();
        request.setAttribute("DAO", dao);
        try{
            id = Integer.parseInt(idd);
            request.setAttribute("user", dao.getUserHm().get(id));
        }catch(Exception e){
            
        }
        if(UserType!=null && !UserType.equals("1")){
            request.getRequestDispatcher("Views/userEdit.jsp").forward(request, response);
            check = true;
        }
        else{
            request.getRequestDispatcher("Views/userUpdate.jsp").forward(request, response);
            check = false;
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        dao.loadRole();
        dao.loadQuestion();
        dao.loadUser();
        dao.loadCate();
        dao.loadNew();
        request.setAttribute("DAO", dao);
        if(check) UserEdit(request, response);
        else try {
            UserUpdate(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void UserEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        boolean active = request.getParameter("active").equals("on");
        
        User newUser = dao.getUserHm().get(userId);
        newUser.setRoleId(roleId);
        newUser.setActive(active);
        
        dao.update(newUser);
        dao.loadUser();
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/userEdit.jsp").forward(request, response);
    }
    
    protected void UserUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int quesId = Integer.parseInt(request.getParameter("quesId"));
        String answer = request.getParameter("answer");
        String email = request.getParameter("email");
        
        Boolean gender = request.getParameter("gender").equals("male");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        
        String address = request.getParameter("address");
        String avatar = request.getParameter("avatar");
        HttpSession session = request.getSession();
        User newUser = (User)session.getAttribute("UserLogined");
        
        newUser.setFullname(fullname);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setQuesId(quesId);
        newUser.setAnswer(answer);
        newUser.setEmail(email);
        newUser.setGender(gender);
        newUser.setDob(dob);
        newUser.setAddress(address);
        newUser.setAvatar(avatar);
        
        dao.update(newUser);
        session.setAttribute("UserLogined", newUser);
        dao.loadUser();
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/userUpdate.jsp").forward(request, response);
    }

    
}
