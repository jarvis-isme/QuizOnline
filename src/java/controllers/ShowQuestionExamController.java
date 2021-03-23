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
import dtos.ExamNotFinishDetailDTO;
import dtos.QuestionDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.sql.Date;
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
@WebServlet(name = "ShowQuestionExamController", urlPatterns = {"/ShowQuestionExamController"})
public class ShowQuestionExamController extends HttpServlet {

    private final static String ERROR = "error.jsp";
    private final static String SUCCESS = "exam.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            ExamNotFinishDAO dao = new ExamNotFinishDAO();
            String subjectId = request.getParameter("txtSubjectId");
            ExamNotFinishDTO dto = dao.checkExam(Integer.parseInt(subjectId), user.getEmail());
            QuestionDAO quesDao = new QuestionDAO();
            AnswerDAO ansDao = new AnswerDAO();
            List<QuestionDTO> listQues = null;
            Map<Integer, List<AnswerDTO>> map = null;
            ExamNotFinishDetailDAO detailDao = new ExamNotFinishDetailDAO();
            Map<Integer, Boolean> chosenMap = null;
            if (dto == null) {
                // chua thi bay h moi bay dau thi 
                long now = System.currentTimeMillis();
                Date currentDate = new Date(now);
                float timeremaing = 50;
                dto = new ExamNotFinishDTO(0, Integer.parseInt(subjectId), user.getEmail(), currentDate, timeremaing);
                dao.insert(dto);
                // init question in exam ;
                listQues = quesDao.getRandomQuestion(Integer.parseInt(subjectId));
                for (QuestionDTO listQue : listQues) {
                    List<AnswerDTO> listAns = ansDao.getAnswerByQuestionId(listQue.getQuestionId());
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(listQue.getQuestionId(), listAns);
                }
                int id = dao.getId(Integer.parseInt(subjectId), user.getEmail());
                for (Integer quesId : map.keySet()) {
                    for (AnswerDTO answerDTO : map.get(quesId)) {
                        ExamNotFinishDetailDTO detail = new ExamNotFinishDetailDTO(id, 0, quesId, answerDTO.getAnswerId(), false);
                        detailDao.insert(detail);
                    }
                }
                dto.setExamNotFinishId(id);
            } else {
                chosenMap = detailDao.getAnswerIsChosen(dto.getExamNotFinishId());
                listQues = quesDao.getListQuestionExam(dto.getExamNotFinishId());
                for (QuestionDTO listQue : listQues) {
                    List<AnswerDTO> listAns = ansDao.getAnswerByQuestionId(listQue.getQuestionId());
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(listQue.getQuestionId(), listAns);
                }
            }
            // get question of exam  from database   beeffore we inited
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            session.setAttribute("LIST_QUESTION_EXAM_USER", listQues);
            session.setAttribute("MAP_ANSWER_EXAM", map);
            session.setAttribute("CHOSEN", chosenMap);
            session.setAttribute("INDEX", Integer.parseInt(index));
            session.setAttribute("TIME", dto);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ShowQuestionExamController:" + e.toString());
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
