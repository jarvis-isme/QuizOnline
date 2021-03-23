/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ExamDAO;
import daos.SubjectDAO;
import dtos.ExamDTO;
import dtos.SubjectDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SearchHistoryController", urlPatterns = {"/SearchHistoryController"})
public class SearchHistoryController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "history.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String subjectId = request.getParameter("txtSubjectId");
            ExamDAO dao = new ExamDAO();
            List<ExamDTO> list = null;
            int count = 0;
            String pages = request.getParameter("his_page");
            if (pages == null || pages.isEmpty()) {
                pages = "1";
            }
            if (subjectId == null || subjectId.isEmpty()) {
                list = dao.getAllHistoty(Integer.parseInt(pages), user.getEmail());
                count = dao.getPageAll(user.getEmail());
            } else {
                list = dao.getHistoyBySearch(Integer.parseInt(subjectId), Integer.parseInt(pages), user.getEmail());
                count = dao.getPageSearch(Integer.parseInt(subjectId), user.getEmail());
            }
            SubjectDAO subDao = new SubjectDAO();
            List<SubjectDTO> listSub = subDao.getSubjectss();
            session.setAttribute("USER_SEARCH",listSub);
            session.setAttribute("HIS", list);
            session.setAttribute("HIS_PAGE", ((count%20)==0)? count/20 : count/20+1);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchHistory" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
