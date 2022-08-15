package Filter;

import DAL.DAO;
import Models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Misaki
 */
public class PageFilter implements Filter {
    
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;
    private HttpServletRequest req;
    
    public PageFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("PageFilter:DoBeforeProcessing");
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("PageFilter:DoAfterProcessing");
        }

    }

    private static final String[] loginRequiredURL = {"/CateUpdate", "/NewsUpdate", "/UserController", "/CommentController", "LikeController", "/ChangePassword"};
    private static final String[] loginNotRequired = {"/LoginController", "/RegisterController", "/ForgotPass"};
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        req = (HttpServletRequest) request;
        
        String path = req.getRequestURI().substring(req.getContextPath().length());
        
        HttpSession session = req.getSession();
        boolean isLoggedIn = (session.getAttribute("UserLogined")!=null);
        
        if(path.endsWith(".jsp"))
        {
            //System.out.println(1);
            req.getRequestDispatcher("/").forward(request, response);
            return;
        }
        
        if(isLoggedIn){
             //System.out.println(2);
            if(path.contains(loginNotRequired[0]) || path.contains(loginNotRequired[1]) || path.contains(loginNotRequired[2])){
                req.getRequestDispatcher("/").forward(request, response);
                 System.out.println(3);
            }
            else{
                 //System.out.println(4);
                User user = (User)session.getAttribute("UserLogined");
                int role = user.getRoleId();
                if(role==1)
                    chain.doFilter(request, response);
                else if(role==2){
                    if(path.contains("/CateUpdate")) 
                        req.getRequestDispatcher("/").forward(request, response);
                    else if(path.contains("/UserController") && path.contains("UserType=0"))
                        req.getRequestDispatcher("/").forward(request, response);
                    else
                        chain.doFilter(request, response);
                      //chain.doFilter(request, response);
                }
                else if(role==3){
                    if(path.contains("/CateUpdate")) 
                        req.getRequestDispatcher("/").forward(request, response);
                    else if(path.contains("/UserController") && path.contains("UserType=0"))
                        req.getRequestDispatcher("/").forward(request, response);
                    else if(path.contains("/NewsUpdate"))
                        req.getRequestDispatcher("/").forward(request, response);
                    else
                        chain.doFilter(request, response);
                }
                   
                    
                else
                    chain.doFilter(request, response);
            }
        }
        else if(!isLoggedIn && Required()){
            req.getRequestDispatcher("/LoginController").forward(request, response);
             System.out.println(5);
        }
        else{
            chain.doFilter(request, response);
        }
    }
    
    private boolean Required(){
        String requestURL = req.getRequestURL().toString();
        for(String s: loginRequiredURL){
            if(requestURL.contains(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("PageFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("PageFilter()");
        }
        StringBuffer sb = new StringBuffer("PageFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
