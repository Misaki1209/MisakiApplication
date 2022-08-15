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
public class LikeController extends HttpServlet {

    DAO dao;
    
    public void init(){
        dao = new DAO();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String articleId = request.getParameter("ArId");
        int newId = Integer.parseInt(articleId);
        //System.out.println(newId);
        String likeBtn = request.getParameter("likeBtn");
        //System.out.println(likeBtn);
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("UserLogined");
        if(likeBtn.equals("0")) 
            dao.UnLike(u.getId(), newId);
        else
            dao.Like(u.getId(), newId);
        
        response.sendRedirect("NewsController");
    }

    
}
