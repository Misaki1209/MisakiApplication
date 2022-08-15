package Controllers;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Misaki
 */
public class ForgotPass extends HttpServlet {

    DAO dao;
    public void init(){
        dao = new DAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dao.loadCate();
        dao.loadNew();
        dao.loadQuestion();
        dao.loadRole();
        dao.loadUser();
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/forgotPass.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username= request.getParameter("username");
        int quesId = Integer.parseInt(request.getParameter("quesId"));
        String answer = request.getParameter("answer");
        String newPass = request.getParameter("newPass");
        int id = dao.getUserToResetPass(username, quesId, answer);
        if(id == -1){
            request.setAttribute("NOTIFICATION", "some thing wrong bro");
            request.setAttribute("DAO", dao);
            request.getRequestDispatcher("Views/forgotPass.jsp").forward(request, response);
        }
        else{
            dao.ChangePass(id, newPass);
            response.sendRedirect("LoginController");
        }
    }

   
}
