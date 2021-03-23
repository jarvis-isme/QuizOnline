/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ExamDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author nguye
 */
public class ExamDetailDAO {

    public void insert(ExamDetailDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert  tblExamDetail(examId,questionName,rightAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3,chosenAnswer) values(?,?,?,?,?,?,?) ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, dto.getExamId());
                pst.setString(2, dto.getQuestioName());
                pst.setString(3, dto.getRightAnswer());
                pst.setString(4, dto.getWrongAnswer1());
                pst.setString(5, dto.getWrongAnswer2());
                pst.setString(6, dto.getWrongAnswer3());
                pst.setString(7, dto.getChosenAnswer());
                pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public List<ExamDetailDTO> getExamDetail(int id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ExamDetailDTO> list = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select questionName,rightAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3,chosenAnswer from tblExamDetail\n"
                        + " where examId=? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String questionName = rs.getString("questionName");
                    String rightAnswer = rs.getString("rightAnswer");
                    String wrongAnswer1 = rs.getString("wrongAnswer1");
                    String wrongAnswer2 = rs.getString("wrongAnswer2");
                    String wrongAnswer3 = rs.getString("wrongAnswer3");
                    String chosenAnswer = rs.getString("chosenAnswer");
                    ExamDetailDTO dto = new ExamDetailDTO(id, id, questionName, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, chosenAnswer);
                    if(list==null){
                        list=new ArrayList<>();
                    }
                    list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
}
