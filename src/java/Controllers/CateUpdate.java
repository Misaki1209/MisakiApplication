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
public class CateUpdate extends HttpServlet {

    DAO dao;
    
    public void init(){
        dao = new DAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("CateType");
        String id = request.getParameter("CateId");
        
        if(type!=null && type.equals("1")){
            dao.deleteCate(Integer.parseInt(id));
        }
        
        dao.loadCate();
        dao.loadNew();
        dao.loadQuestion();
        dao.loadRole();
        dao.loadUser();
        
        if(type!=null && type.equals("0")){
            request.setAttribute("Category", dao.getCateHm().get(Integer.parseInt(id)));
            //System.out.println(dao.getCateHm().get(id).getId());
        }
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/cateEdit.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("CateId");
        String name = request.getParameter("CateName");
        
        if(id!=null && id.length()!=0) dao.updateCate(Integer.parseInt(id),name);
        else dao.insertCate(name);
        
        dao.loadCate();
        request.setAttribute("DAO", dao);
        request.getRequestDispatcher("Views/cateEdit.jsp").forward(request, response);
    }

}
