/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AnswerDAO;
import daos.QuestionDAO;
import daos.SubjectDAO;
import dtos.AnswerDTO;
import dtos.QuestionDTO;
import dtos.SubjectDTO;
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
@WebServlet(name = "ShowUpdatePageController", urlPatterns = {"/ShowUpdatePageController"})
public class ShowUpdatePageController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "updatepage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String questionId = request.getParameter("txtQuestionId");
            AnswerDAO dao = new AnswerDAO();
            SubjectDAO subDao = new SubjectDAO();
            HttpSession session = request.getSession();
            QuestionDAO quesDao = new QuestionDAO();
            QuestionDTO dto = quesDao.getQuestionById(Integer.parseInt(questionId));
            List<SubjectDTO> listSub = subDao.getSubjectss();
            List<AnswerDTO> list = dao.getAnswerByQuestionId(Integer.parseInt(questionId));
            session.setAttribute("QUESTION_UPDATE", dto);
            session.setAttribute("ANSWER_UPDATE", list);
            session.setAttribute("SUBJECT_LIST_ADMIN", listSub);
            url = SUCCESS;
        } catch (Exception e) {
            log("Errort at ShowUpdatePage" + e.toString());
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
