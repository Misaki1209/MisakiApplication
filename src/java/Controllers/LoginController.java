package Controllers;

import DAL.*;
import Models.*;
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
public class LoginController extends HttpServlet {

    private DAO dao;

    public void init() {
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
        request.getRequestDispatcher("Views/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LOGIN(request, response);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    private void LOGIN(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        User userLogined = dao.ValidateLogin(username, password);

        if (userLogined != null) {
            if (userLogined.isVerify() && userLogined.isActive()) {
                session.setAttribute("UserLogined", userLogined);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                if (!userLogined.isVerify()) {
                    sendEmail sm = new sendEmail();
                    String code = sm.getRandom();

                    boolean test = sm.SendEmail(userLogined.getEmail(), code);
                    //System.out.println(test);
                    if (test) {
                        if (session.getAttribute("verifying") != null) {
                            session.removeAttribute("verifying");
                        }
                        session.setAttribute("verifying", code);
                        if (session.getAttribute("verifyingUsername") != null) {
                            session.removeAttribute("verifyingUsername");
                        }
                        session.setAttribute("verifyingUsername", username);
                        request.getRequestDispatcher("Views/Verify.jsp").forward(request, response);
                    }
                } else if (!userLogined.isActive()) {
                    request.setAttribute("DAO", dao);
                    request.setAttribute("NOTIFICATION", "This account is inactive!");
                    request.getRequestDispatcher("Views/login.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("DAO", dao);
            request.setAttribute("NOTIFICATION", "Wrong username or password!");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
        }
    }

}
