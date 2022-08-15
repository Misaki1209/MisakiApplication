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
import java.text.DateFormat;
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
public class RegisterController extends HttpServlet {

    DAO dao;

    public void init() {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        dao.loadCate();
        dao.loadQuestion();
        dao.loadUser();
        dao.loadNew();
        dao.loadRole();
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            register(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int quesId = Integer.parseInt(request.getParameter("quesId"));
        String answer = request.getParameter("answer");
        String email = request.getParameter("email");

        boolean gender = request.getParameter("gender").equals("male");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));

        String address = request.getParameter("address");
        String avatar = request.getParameter("avatar");
        Boolean verify = false;
        User newUser = new User(0, fullname, username, password, 3, quesId, answer, email, gender, false, dob, address, avatar, verify);

        if (!dao.ValidateUsername(username)) {
            request.setAttribute("NOTIFICATION", "Username existed!");
            request.setAttribute("DAO",dao);
            request.getRequestDispatcher("Views/register.jsp").forward(request, response);
        } else if (!dao.ValidateMail(email)) {
            request.setAttribute("NOTIFICATION", "Email existed!");
            request.setAttribute("DAO",dao);
            request.getRequestDispatcher("Views/register.jsp").forward(request, response);
        } else {
            dao.Insert(newUser);

            sendEmail sm = new sendEmail();
            String code = sm.getRandom();

            boolean test = sm.SendEmail(email, code);
            //System.out.println(test);
            if (test) {
                HttpSession session = request.getSession();
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
        }
    }

}
