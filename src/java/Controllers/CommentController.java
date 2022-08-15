/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAL.DAO;
import Models.Comment;
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
 * @author Admin
 */
public class CommentController extends HttpServlet {

    DAO dao;

    public void init() {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("UserLogined");
        int userId = u.getId();
        int articleId = Integer.parseInt(request.getParameter("ArId"));

        if (request.getParameter("addComment") != null) {
            
            String comment = request.getParameter("comment");
            dao.Comment(userId, articleId, comment);
        } 
        else if (request.getParameter("deleComment") != null) {
            int cmtId = Integer.parseInt(request.getParameter("cmtId"));
            dao.DeleComment(cmtId, articleId);
        } 
        else if (request.getParameter("editComment1") != null) {
            int cmtId = Integer.parseInt(request.getParameter("cmtId"));
            dao.loadComment();
            for(Comment c: dao.getCmtList()){
                if(c.getId() == cmtId){
                    request.setAttribute("editCmt", c);
                    break;
                }
            } 
        }
        else if(request.getParameter("editComment2") != null){
            System.out.println("1");
            int cmtId = Integer.parseInt(request.getParameter("cmtId"));
            System.out.println("2");
            String cmt = request.getParameter("comment");
            dao.updateComment(cmtId, cmt);
        }

        dao.loadCate();
        dao.loadComment();
        dao.loadNew();
        dao.loadLike();
        dao.loadUser();
        request.setAttribute("DAO", dao);
        request.setAttribute("newId", articleId);
        request.getRequestDispatcher("Views/new.jsp").forward(request, response);
    }

}
