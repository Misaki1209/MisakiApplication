/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAL.DAO;
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
 * @author Admin
 */
public class NewsUpdate extends HttpServlet {

    DAO dao;

    public void init() {
        dao = new DAO();
    }

    int check=-1;
    int ArticleId=-1;
    News newActicle;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("addNew")!=null) check=0;
        else if(request.getParameter("edit")!=null) check=1;
        else if(request.getParameter("delete")!=null) check=2;
        else if(request.getParameter("active")!=null) check=3;
        else if(request.getParameter("addNew2")!= null) check = 4;
        else if(request.getParameter("edit2")!=null) check = 5;
        else check = -1;
        
        //System.out.println(check);
        if(check==-1){
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }
        if(check!=0 && check!=4) ArticleId = Integer.parseInt(request.getParameter("ArticleId"));
        
        if (check == 2) {
            dao.Delete(ArticleId);
        }
        
        if(check == 4 || check ==5){
            doSmt(request, response);
            return;
        }
        
        dao.loadCate();
        dao.loadNew();
        dao.loadQuestion();
        dao.loadRole();
        dao.loadUser();

        if (check != 0 && check!=2) {
            for (News n : dao.getnList()) {
                if (n.getId() == ArticleId) {
                    newActicle = n;
                    //System.out.println(n.getImage());
                    //System.out.println(n.getCateId());
                    break;
                }
            }

        }

        if (check == 1) {
            request.setAttribute("updateArticle", newActicle);
        } else if (check == 3) {
            dao.ArticleActive(ArticleId, !newActicle.isActive());
            dao.loadNew();
        }

        request.setAttribute("DAO", dao);
        
        if (check <= 1) {
            request.getRequestDispatcher("Views/newsEdit.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }
    
    private void doSmt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("title");
        String description = request.getParameter("description");
        String detail = request.getParameter("detail");
        String image = request.getParameter("image");

        int cateId = Integer.parseInt(request.getParameter("cateId"));
        //int cateId = 1;
        HttpSession session = request.getSession();
        User UserLogined = (User) session.getAttribute("UserLogined");
        if (check == 4) {
            newActicle = new News(0, cateId, name, description, detail, image,
                    java.time.LocalDate.now(), UserLogined.getId(), true, 0, 0);
            dao.Insert(newActicle);
        } else if (check == 5) {
            newActicle.setName(name);
            newActicle.setDescription(description);
            newActicle.setDetail(detail);
            newActicle.setImage(image);
            newActicle.setCateId(cateId);
            dao.Update(newActicle);
        }

        dao.loadCate();
        dao.loadNew();
        dao.loadQuestion();
        dao.loadRole();
        dao.loadUser();

        //session.setAttribute("reload", true);
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
