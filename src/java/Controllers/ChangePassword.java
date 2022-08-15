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

/**
 *
 * @author Misaki
 */
public class ChangePassword extends HttpServlet {

    DAO dao;
    
    @Override
    public void init(){
        dao = new DAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dao.loadCate();
        dao.loadNew();
        dao.loadUser();
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/ChangePass.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        
        User userLogined = (User)session.getAttribute("UserLogined");
        if(oldPass.equals(userLogined.getPassword())){
            dao.ChangePass(userLogined.getId(), newPass);
            response.sendRedirect("NewsController");
        }
        else{
            request.setAttribute("NOTIFICATION", "wrong password!");
            request.setAttribute("DAO", dao);
            request.getRequestDispatcher("Views/ChangePass.jsp").forward(request, response);
        }
    }

}
