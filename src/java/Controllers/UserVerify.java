package Controllers;

import DAL.DAO;
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
public class UserVerify extends HttpServlet {
//    DAO dao;
//    public void init(){
//        dao = new DAO();
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("VerifyCode");
        HttpSession session = request.getSession();
        String verifying = session.getAttribute("verifying").toString();
//            dao.loadCate();
//            dao.loadComment();
//            dao.loadLike();
//            dao.loadNew();
//            dao.loadQuestion();
//            dao.loadRole();
//            dao.loadUser();
//            request.setAttribute("DAO", dao);
        if(verifying.equals(code)){
            //request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            DAO dao = new DAO();
            dao.VerifyUser(session.getAttribute("verifyingUsername").toString());
            response.sendRedirect(request.getContextPath() + "/LoginController");
        }
        else{
            request.setAttribute("NOTIFICATION", "Wrong code!");
            response.sendRedirect("Views/Verify.jsp");
        }
    }

    

}
