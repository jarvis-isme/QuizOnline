/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AnswerDAO;
import daos.QuestionDAO;
import dtos.AnswerDTO;
import dtos.QuestionDTO;
import java.io.IOException;
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
@WebServlet(name = "UpdateQuestionController", urlPatterns = {"/UpdateQuestionController"})
public class UpdateQuestionController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchQuestionController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String questionId = request.getParameter("txtQuestionId");
            String questionName = request.getParameter("txtQuestionName");
            String isDeleted = request.getParameter("txtIsDeleted");
            String subjectId = request.getParameter("txtSubject");
            String[] answerId = request.getParameterValues("txtAnswerId");
            String[] answerName = request.getParameterValues("txtAnswer");
            QuestionDAO dao = new QuestionDAO();
            AnswerDAO ansDao = new AnswerDAO();
            QuestionDTO question = new QuestionDTO(Integer.parseInt(questionId), Integer.parseInt(subjectId), questionName, null, Boolean.parseBoolean(isDeleted));
            dao.update(question);
            if (answerId != null) {
                for (int i = 0; i < answerId.length; i++) {
                    AnswerDTO answer = new AnswerDTO(answerName[i], i, Integer.parseInt(answerId[i]), true);
                    ansDao.update(answer);
                }
            }
            session.setAttribute("QUESTION_UPDATE", null);
            session.setAttribute("ANSWER_UPDATE", null);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at UpdateQuestionController:" + e.toString());
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
