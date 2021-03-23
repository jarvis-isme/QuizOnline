/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.AnswerDTO;
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
public class AnswerDAO {

    public void insert(AnswerDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert tblAnswer(answerName,isRight,questionId) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getAnswerName());
                pst.setBoolean(2, dto.isIsRight());
                pst.setInt(3, dto.getQuestionId());
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

    public List<AnswerDTO> getAnswerByQuestionId(int questionId) throws SQLException {
        List<AnswerDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select A.answerId,A.isRight,A.answerName\n"
                        + "from tblAnswer A , tblQuestion Q\n"
                        + "where A.questionId=Q.questionId and A.questionId=? ";
                pst = cn.prepareCall(sql);
                pst.setInt(1, questionId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int answerId = rs.getInt("answerId");
                    String answerName = rs.getString("answerName");
                    boolean isRight = rs.getBoolean("isRight");
                    AnswerDTO dto = new AnswerDTO(answerName, answerId, questionId, isRight);
                    if (list == null) {
                        list = new ArrayList<>();
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

    public void update(AnswerDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblAnswer set answerName=? where answerId=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getAnswerName());
                pst.setInt(2, dto.getQuestionId());
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
}
