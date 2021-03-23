/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ExamNotFinishDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author nguye
 */
public class ExamNotFinishDAO {

    public ExamNotFinishDTO checkExam(int subjectId, String email) throws SQLException {
        ExamNotFinishDTO dto = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select examNotFinishId,timeremaing,testedDate from tblExamNotFinish where subjectId=? and email=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                pst.setString(2, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int examNotFinishId = rs.getInt("examNotFinishId");
                    float timeremainnig = rs.getFloat("timeremaing");
                    Date testedDate = rs.getDate("testedDate");
                    dto = new ExamNotFinishDTO(examNotFinishId, subjectId, email, testedDate, timeremainnig);
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

        return dto;
    }

    public void insert(ExamNotFinishDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert  tblExamNotFinish(timeremaing,testedDate,subjectId,email) values(?,?,?,?) ";
                pst = cn.prepareStatement(sql);
                pst.setFloat(1, dto.getTimeremaining());
                pst.setDate(2, dto.getTestedDate());
                pst.setInt(3, dto.getSubjectId());
                pst.setString(4, dto.getEmail());
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

    public int getId(int subjectId, String email) throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select examNotFinishId from tblExamNotFinish where subjectId=? and email=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                pst.setString(2, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("examNotFinishId");
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

        return result;
    }

    public void delete(int id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "delete from tblExamNotFinish where examNotFinishId=? ";
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
      public void update(ExamNotFinishDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblExamNotFinish set timeremaing=? where examNotFinishId=?";
                pst = cn.prepareStatement(sql);
                pst.setFloat(1, dto.getTimeremaining());
                pst.setInt(2,dto.getExamNotFinishId());
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
