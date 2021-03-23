/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ExamNotFinishDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import utils.DBUtils;

/**
 *
 * @author nguye
 */
public class ExamNotFinishDetailDAO {

    public void insert(ExamNotFinishDetailDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert tblExamNotFinishDetail(examNotFinishId,isChoosen,questionId,answerId) values(?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, dto.getExamNotFinishId());
                pst.setBoolean(2, dto.isIsChoosen());
                pst.setInt(3, dto.getQuestionId());
                pst.setInt(4, dto.getAnswerId());
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

    public Map<Integer, Boolean> getAnswerIsChosen(int id) throws SQLException {
        Map<Integer, Boolean> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select answerId \n"
                        + "from tblExamNotFinishDetail \n"
                        + "where examNotFinishId=? and isChoosen=1\n"
                        + "";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new HashMap<>();
                    }
                    int answerId = rs.getInt("answerId");
                    list.put(answerId, true);
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

    public void updateNotChooseALl(int questionId,int examNotFinishId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblExamNotFinishDetail set isChoosen=0 where questionId=? and examNotFinishId=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, questionId);
                pst.setInt(2,examNotFinishId);
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

    public void chooseAns(int answerId,int examNotFinishId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblExamNotFinishDetail set isChoosen=1 where answerId=? and examNotFinishId=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, answerId);
                pst.setInt(2,examNotFinishId);
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

    public void delete(int id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "delete from tblExamNotFinishDetail where examNotFinishId=? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1 , id);
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
