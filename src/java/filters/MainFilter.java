/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
@WebFilter(filterName = "MainFilter", urlPatterns = {"/*"})
public class MainFilter implements Filter {

    private static final boolean DEBUG = true;
    private Map<String, String> mapping;
    private FilterConfig filterConfig = null;
    private List<String> listUnauthen;
    private List<String> listUser;
    private List<String> listAdmin;

    public MainFilter() {
        //adding mapping
        mapping = new HashMap<>();
        mapping.put("login", "LoginController");
        mapping.put("register", "RegisterController");
        mapping.put("errorpage", "error.jsp");
        mapping.put("loginpage", "index.jsp");
        mapping.put("registerpage", "register.jsp");
        //nao cung dc
        mapping.put("logout", "LogOutController");
        mapping.put("homepage", "ShowHomePageController");
        //admin
        mapping.put("addquestion", "AddQuestionController");
        mapping.put("searchquestion", "SearchQuestionController");
        mapping.put("deletequestion", "DeleteQuestionController");
        mapping.put("updatepage", "ShowUpdatePageController");
        mapping.put("updatequestion", "UpdateQuestionController");
        //user
        mapping.put("quiz", "ShowQuestionExamController");
        mapping.put("changequiz", "UpdateExamController");
        mapping.put("searchhistory", "SearchHistoryController");
        mapping.put("preview","PreviewController");
        // add role Unauthen
        listUnauthen = new ArrayList<>();
        listUnauthen.add("login");
        listUnauthen.add("register");
        listUnauthen.add("errorpage");
        listUnauthen.add("registerpage");
        listUnauthen.add("logout");
        // add admin role
        listAdmin = new ArrayList<>();
        listAdmin.add("addquestion");
        listAdmin.add("searchquestion");
        listAdmin.add("deletequestion");
        listAdmin.add("updatepage");
        listAdmin.add("updatequestion");
        listAdmin.add("logout");
        listAdmin.add("homepage");
        //add user
        listUser = new ArrayList<>();
        listUser.add("quiz");
        listUser.add("changequiz");
        listUser.add("searchhistory");
        listUser.add("logout");
        listUser.add("preview");
        listUser.add("homepage");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            log("MainFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            log("MainFilter:DoAfterProcessing");
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        try {
            HttpSession session = req.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            String url = null;
            if (resource.contains(".jsp")) {
                if (user == null) {
                    url = mapping.get("loginpage");
                } else {
                    url = mapping.get("homepage");
                }
                 req.getRequestDispatcher(url).forward(request, response);
            } else {
                if (user == null) {
                    if (listUnauthen.contains(resource)) {
                        url = mapping.get(resource);
                    }
                } else if (user.getRoleId().equals("US")) {
                    if (listUser.contains(resource)) {
                        url = mapping.get(resource);
                    }
                }else if(user.getRoleId().equals("AD")){
                     if (listAdmin.contains(resource)) {
                        url = mapping.get(resource);
                    }
                }
               
                if (url != null) {
                    req.getRequestDispatcher(url).forward(request, response);
                } else {
                   if(user==null){
                        req.getRequestDispatcher("index.jsp").forward(request, response);
                   }else{
                           req.getRequestDispatcher(mapping.get("homepage")).forward(request, response);
                   }

                }
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            log("Error at MainFilter" + e.toString());
        }
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
            if (DEBUG) {
                log("MainFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MainFilter()");
        }
        StringBuffer sb = new StringBuffer("MainFilter(");
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
