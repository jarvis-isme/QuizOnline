/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AnswerDAO;
import daos.ExamNotFinishDAO;
import daos.ExamNotFinishDetailDAO;
import daos.QuestionDAO;
import dtos.AnswerDTO;
import dtos.ExamNotFinishDTO;
import dtos.QuestionDTO;
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
@WebServlet(name = "UpdateExamController", urlPatterns = {"/UpdateExamController"})
public class UpdateExamController extends HttpServlet {

    private final static String ERROR = "error.jsp";
    private final static String SUCCESS = "exam.jsp";
    private final static String SUBMIT = "FinishExamController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ExamNotFinishDTO dto = (ExamNotFinishDTO) session.getAttribute("TIME");
            String index = request.getParameter("index");
            String check = request.getParameter("check");
            String time = request.getParameter("clock1");
            ExamNotFinishDetailDAO dao = new ExamNotFinishDetailDAO();
            QuestionDAO quesDao = new QuestionDAO();
            AnswerDAO ansDao = new AnswerDAO();
            if (check != null) {
                String questionId = request.getParameter("txtQuestionId");
                dao.updateNotChooseALl(Integer.parseInt(questionId), dto.getExamNotFinishId());
                dao.chooseAns(Integer.parseInt(check), dto.getExamNotFinishId());
                Map<Integer, List<AnswerDTO>> map = null;
                Map<Integer, Boolean> chosenMap = null;
                List<QuestionDTO> listQues = quesDao.getListQuestionExam(dto.getExamNotFinishId());
                chosenMap = dao.getAnswerIsChosen(dto.getExamNotFinishId());
                for (QuestionDTO listQue : listQues) {
                    List<AnswerDTO> listAns = ansDao.getAnswerByQuestionId(listQue.getQuestionId());
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(listQue.getQuestionId(), listAns);
                }

                session.setAttribute("LIST_QUESTION_EXAM_USER", listQues);
                session.setAttribute("MAP_ANSWER_EXAM", map);
                session.setAttribute("CHOSEN", chosenMap);
            }
            String[] timeList = time.split(":");
            float minutes = Float.parseFloat(timeList[0]);
            float seconds = Float.parseFloat(timeList[1]);
            dto.setTimeremaining(minutes+(float)seconds/60);
            ExamNotFinishDAO notfinishDao=new ExamNotFinishDAO();
            notfinishDao.update(dto);
            session.setAttribute("TIME", dto);
            session.setAttribute("INDEX", Integer.parseInt(index));
            if (Integer.parseInt(index) != 0) {
                url = SUCCESS;
            } else {
                url = SUBMIT;
            }

        } catch (Exception e) {
            log("Error at UpdateExamController" + e.toString());
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
