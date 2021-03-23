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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "SearchQuestionController", urlPatterns = {"/SearchQuestionController"})
public class SearchQuestionController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String pages = request.getParameter("txtPage");
            String action = request.getParameter("txtAction");
            AnswerDAO ansDao = new AnswerDAO();
            SubjectDAO dao = new SubjectDAO();
            QuestionDAO quesDao = new QuestionDAO();
            List<SubjectDTO> list = dao.getSubjectss();
            HttpSession session = request.getSession();
            Map<Integer, List<QuestionDTO>> map = null;
            Map<Integer, List<AnswerDTO>> mapAnswer = null;
            int count = 0;
            if (action == null || action.isEmpty()) {
                action = "Name";
            }
            if (pages == null) {
                pages = "1";
            }
            if (action.equals("Name")) {
                String questionName = request.getParameter("txtQuestionName");
                if (questionName == null) {
                    questionName = "";
                }

                map = quesDao.searchName(questionName, Integer.parseInt(pages));
                count = quesDao.getPagesSearchName(questionName);

            }
            if (action.equals("Subject")) {
                String subjectId = request.getParameter("txtSubject");
                 
                map = quesDao.searchSubject(Integer.parseInt(subjectId), Integer.parseInt(pages));
                count = quesDao.getPagesSearchSubject(Integer.parseInt(subjectId));
            }
            if (action.equals("IsActive")) {
                String isDeleted = request.getParameter("txtIsActive");
                map = quesDao.searchDeleted(Boolean.parseBoolean(isDeleted), Integer.parseInt(pages));
                count = quesDao.getPagesSearchDeleted(Boolean.parseBoolean(isDeleted));
            }
            if (map != null) {
                for (Integer key : map.keySet()) {
                    List<QuestionDTO> listQues = map.get(key);
                    for (QuestionDTO obj : listQues) {
                        List<AnswerDTO> listAns = ansDao.getAnswerByQuestionId(obj.getQuestionId());
                        if (mapAnswer == null) {
                            mapAnswer = new HashMap<>();
                        }
                        mapAnswer.put(obj.getQuestionId(), listAns);
                    }
                }
            }
            session.setAttribute("MAP_SUBJECT", map);
            session.setAttribute("MAP_ANSWER", mapAnswer);
            session.setAttribute("SUBJECT_LIST_ADMIN", list);
            session.setAttribute("PAGES", ((count % 20) == 0) ? (count / 20) : (count / 20 + 1));
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchQuestionController:" + e.toString());
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
