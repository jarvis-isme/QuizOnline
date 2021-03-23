/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ExamDTO;
import java.sql.Connection;
import java.sql.Date;
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
public class ExamDAO {

    public void insert(ExamDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert  tblExam(totalRight,mark,subjectId,email,testedDate) values(?,?,?,?,?) ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, dto.getTotalRight());
                pst.setFloat(2, dto.getMark());
                pst.setInt(3, dto.getSubjectId());
                pst.setString(4, dto.getEmail());
                pst.setDate(5, dto.getTestedDate());
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
                String sql = " select max(examId) as max from tblExam\n"
                        + "where subjectId=? and email=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                pst.setString(2, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("max");
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

    public List<ExamDTO> getAllHistoty(int page, String email) throws SQLException {
        List<ExamDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " select examId,subjectId,testedDate,totalRight,mark\n"
                        + "from tblExam\n"
                        + "where email = ? "
                        + "order by subjectId\n"
                        + "OFFSET (? - 1) * 20 ROWS FETCH NEXT 20 ROWS ONLY\n";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setInt(2, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    int examId = rs.getInt("examId");
                    Date testedDate = rs.getDate("testedDate");
                    int totalRight = rs.getInt("totalRight");
                    float mark = rs.getFloat("mark");
                    int subjectId = rs.getInt("subjectId");
                    ExamDTO exam = new ExamDTO(examId, subjectId, testedDate, totalRight, mark, email);
                    list.add(exam);
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

    public int getPageAll(String email) throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " select count(examId) as max from tblExam\n"
                        + "where  email=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("max");
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

    public List<ExamDTO> getHistoyBySearch(int subjectId, int page, String email) throws SQLException {
        List<ExamDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " select examId,subjectId,testedDate,totalRight,mark\n"
                        + "from tblExam\n"
                        + "where email =? and subjectId=? "
                        + "order by subjectId\n"
                        + "OFFSET (?-1)*20 ROWS FETCH NEXT 20 ROWS ONLY\n";
                pst = cn.prepareStatement(sql);
                pst.setInt(3, page);
                pst.setString(1, email);
                pst.setInt(2, subjectId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    int examId = rs.getInt("examId");
                    Date testedDate = rs.getDate("testedDate");
                    int totalRight = rs.getInt("totalRight");
                    float mark = rs.getFloat("mark");
                    ExamDTO exam = new ExamDTO(examId, subjectId, testedDate, totalRight, mark, email);
                    list.add(exam);
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

    public int getPageSearch(int subjectId, String email) throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " select count(examId) as max from tblExam\n"
                        + "where subjectId=? and email=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                pst.setString(2, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("max");
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
}
