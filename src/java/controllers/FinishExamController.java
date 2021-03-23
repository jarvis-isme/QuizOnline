/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AnswerDAO;
import daos.ExamDAO;
import daos.ExamDetailDAO;
import daos.ExamNotFinishDAO;
import daos.ExamNotFinishDetailDAO;
import daos.QuestionDAO;
import dtos.AnswerDTO;
import dtos.ExamDTO;
import dtos.ExamDetailDTO;
import dtos.ExamNotFinishDTO;
import dtos.QuestionDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "FinishExamController", urlPatterns = {"/FinishExamController"})
public class FinishExamController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "notify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ExamNotFinishDTO dto = (ExamNotFinishDTO) session.getAttribute("TIME");
            ExamNotFinishDetailDAO dao = new ExamNotFinishDetailDAO();
            QuestionDAO quesDao = new QuestionDAO();
            AnswerDAO ansDao = new AnswerDAO();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            Map<Integer, List<AnswerDTO>> map = null;
            Map<Integer, Boolean> chosenMap = dao.getAnswerIsChosen(dto.getExamNotFinishId());
            List<QuestionDTO> listQues = quesDao.getListQuestionExam(dto.getExamNotFinishId());
            for (QuestionDTO listQue : listQues) {
                List<AnswerDTO> listAns = ansDao.getAnswerByQuestionId(listQue.getQuestionId());
                if (map == null) {
                    map = new HashMap<>();
                }
                map.put(listQue.getQuestionId(), listAns);
            }
            int totalRight = 0;
            List<ExamDetailDTO> list = new ArrayList<>();
            for (QuestionDTO listQue : listQues) {
                String questionName = listQue.getQuestionName();
                int count = 0;
                String rightAnswer = "";
                String[] wrongAnswer = new String[3];
                String chosenAnswer = "Not choose";
                for (AnswerDTO answerDTO : map.get(listQue.getQuestionId())) {
                    if (answerDTO.isIsRight()) {
                        rightAnswer = answerDTO.getAnswerName();
                    } else {
                        wrongAnswer[count] = answerDTO.getAnswerName();
                        count++;
                    }
                    if (chosenMap != null) {
                        if (chosenMap.containsKey(answerDTO.getAnswerId())) {
                            chosenAnswer = answerDTO.getAnswerName();
                            totalRight++;
                        }
                    }
                }
                ExamDetailDTO obj = new ExamDetailDTO(0, totalRight, questionName, rightAnswer, wrongAnswer[0], wrongAnswer[1], wrongAnswer[2], chosenAnswer);
                list.add(obj);
            }
            ExamDTO exam = new ExamDTO(0, dto.getSubjectId(), dto.getTestedDate(), totalRight, (float)totalRight / 50, user.getEmail());
            ExamDAO examDao = new ExamDAO();
            examDao.insert(exam);
            int id = examDao.getId(exam.getSubjectId(), exam.getEmail());
            for (ExamDetailDTO obj : list) {
                obj.setExamId(id);
                ExamDetailDAO examDetailDao = new ExamDetailDAO();
                examDetailDao.insert(obj);
            }
            dao.delete(dto.getExamNotFinishId());
            ExamNotFinishDAO examNotFinishDao=new ExamNotFinishDAO();
            examNotFinishDao.delete(dto.getExamNotFinishId());
            session.setAttribute("LIST_QUESTION_EXAM_USER", null);
            session.setAttribute("MAP_ANSWER_EXAM", null);
            session.setAttribute("CHOSEN", null);
            request.setAttribute("NOTIFY", "You have got "+totalRight+"/50 and mark is :"+(float)totalRight/50);
            url=SUCCESS;
        } catch (Exception e) {
            log("Error at FinishExamController" + e.toString());
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
