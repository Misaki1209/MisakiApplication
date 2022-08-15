package Controllers;

import DAL.*;
import Models.Like;
import Models.PageInfor;
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
public class NewsController extends HttpServlet {

    DAO dao;
    boolean[] likeArr = new boolean[10000];

    public void init() {
        dao = new DAO();
    }

    private void loadData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        dao.loadNew();
        dao.loadCate();
        dao.loadQuestion();
        dao.loadRole();
        dao.loadUser();
        dao.loadLike();
        dao.loadComment();
        
         for(int i=0;i<10000;i++) likeArr[i] = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("UserLogined") != null) {
            User u = (User) session.getAttribute("UserLogined");
            int id = u.getId();
            for (Like li : dao.getLikeList()) {
                if (li.getUserId() == id) {
                    likeArr[li.getNewId()] = true;
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        if (type != null && type.equals("0")) {
            try {
                int idd = Integer.parseInt(id);
                One_category(request, response, idd);
            } catch (Exception e) {

            }

        } else if (type != null && type.equals("1")) {
            try {
                int idd = Integer.parseInt(id);
                One_new(request, response, idd);
            } catch (Exception e) {

            }
        } else if (type != null && type.equals("2")) {
            try {
                int idd = Integer.parseInt(id);
                One_user(request, response, idd);
            } catch (Exception e) {
            }
        } else {

            loadData(request, response);
            request.setAttribute("DAO", dao);
            
            int size = dao.getnList().size();
            int cp = 0;
            int nrpp = 3;
            
            if(session.getAttribute("nrpp")!= null) 
                nrpp = (int)session.getAttribute("nrpp");
            request.setAttribute("CP", new PageInfor(cp,nrpp,size));
            request.setAttribute("likeArr", likeArr);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int np = Integer.parseInt(request.getParameter("np"));
        int cp = Integer.parseInt(request.getParameter("cp"));
        
        int nrpp=3;
        nrpp=Integer.parseInt(request.getParameter("nrpp"));
        HttpSession session = request.getSession();
        session.setAttribute("nrpp", nrpp);
        loadData(request, response);
        
        if(request.getParameter("home")!=null) cp=0;
        if(request.getParameter("pre")!=null) cp--;
        if(request.getParameter("next")!=null) cp++;
        if(request.getParameter("end")!=null) cp=np-1;
        
        for(int i=0; i<np; i++){
            if(request.getParameter("btn" + i) != null) cp=i;
        }
        
        PageInfor page = new PageInfor(cp,nrpp,dao.getnList().size());
        request.setAttribute("DAO", dao);
        request.setAttribute("CP", page);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void One_category(HttpServletRequest request, HttpServletResponse response, int cateId)
            throws ServletException, IOException {

        loadData(request, response);

        request.setAttribute("DAO", dao);
        request.setAttribute("likeArr", likeArr);
        request.setAttribute("cateId", cateId);
        request.getRequestDispatcher("Views/category.jsp").forward(request, response);
    }

    protected void One_new(HttpServletRequest request, HttpServletResponse response, int newId)
            throws ServletException, IOException {

        loadData(request, response);

        request.setAttribute("DAO", dao);
        request.setAttribute("likeArr", likeArr);
        request.setAttribute("newId", newId);
        request.getRequestDispatcher("Views/new.jsp").forward(request, response);
    }

    protected void One_user(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {

        loadData(request, response);

        request.setAttribute("DAO", dao);
        request.setAttribute("likeArr", likeArr);
        request.setAttribute("userId", userId);
        request.getRequestDispatcher("Views/acticleFromAnUser.jsp").forward(request, response);
    }
}
