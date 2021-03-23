/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.QuestionDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.DBUtils;

/**
 *
 * @author nguye
 */
public class QuestionDAO {

    public void insert(QuestionDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert tblQuestion(questionName,createdDate,subjectId,isDeleted) values(?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getQuestionName());
                pst.setDate(2, dto.getCreatedDate());
                pst.setInt(3, dto.getSubjectId());
                pst.setBoolean(4, dto.isIsDeleted());
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

    public int getQuestionId() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select max(questionId) as max from tblQuestion";
                pst = cn.prepareStatement(sql);
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

    public Map<Integer, List<QuestionDTO>> searchSubject(int subjectId, int page) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Map<Integer, List<QuestionDTO>> list = null;
        List<QuestionDTO> listQues = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select  Q.questionName,Q.questionId,S.subjectId,Q.createdDate,Q.isDeleted\n"
                        + "from tblQuestion Q, tblSubject S\n"
                        + "where Q.subjectId=S.subjectId and Q.subjectId=? \n"
                        + "order by S.subjectId\n"
                        + "OFFSET (? - 1)*20 ROWS FETCH NEXT 20 ROWS ONLY";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                pst.setInt(2, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (listQues == null) {
                        listQues = new ArrayList<>();
                    }
                    String questionName = rs.getString("questionName");
                    int questionId = rs.getInt("questionId");
                    Date createdDate = rs.getDate("createdDate");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, questionName, createdDate, isDeleted);
                    listQues.add(dto);
                }
                if (list == null) {
                    list = new HashMap<>();
                }
                list.put(subjectId, listQues);
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

    public int getPagesSearchSubject(int subjectId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select count(Q.questionId) as count\n"
                        + "from tblQuestion Q, tblSubject S\n"
                        + "where Q.subjectId=S.subjectId and Q.subjectId=? \n"
                        + " ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("count");
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

    public void delete(int questionId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblQuestion set isDeleted=1 where questionId=? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, questionId);
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

    public QuestionDTO getQuestionById(int questionId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        QuestionDTO dto = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select questionName,createdDate,subjectId,isDeleted from tblQuestion where questionId=?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, questionId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String questionName = rs.getString("questionName");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    Date createdDate = rs.getDate("createdDate");
                    int subjectId = rs.getInt("subjectId");
                    dto = new QuestionDTO(questionId, subjectId, questionName, createdDate, isDeleted);
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

    public Map<Integer, List<QuestionDTO>> searchName(String subjectName, int page) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Map<Integer, List<QuestionDTO>> list = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select  Q.questionName,Q.questionId,S.subjectId,Q.createdDate,Q.isDeleted\n"
                        + "from tblQuestion Q, tblSubject S\n"
                        + "where Q.subjectId=S.subjectId and Q.questionName like N'%" + subjectName + "%'"
                        + "order by S.subjectId\n"
                        + "OFFSET (? - 1)*20 ROWS FETCH NEXT 20 ROWS ONLY";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new HashMap<>();
                    }
                    String questionName = rs.getString("questionName");
                    int questionId = rs.getInt("questionId");
                    Date createdDate = rs.getDate("createdDate");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    int subjectId = rs.getInt("subjectId");
                    List<QuestionDTO> listQues = list.get(subjectId);
                    if (listQues == null) {
                        listQues = new ArrayList<>();
                    }
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, questionName, createdDate, isDeleted);
                    listQues.add(dto);
                    list.put(subjectId, listQues);
                    list.size();
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

    public int getPagesSearchName(String subjectName) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select count(Q.questionId) as count\n"
                        + "from tblQuestion Q, tblSubject S\n"
                        + "where Q.subjectId=S.subjectId and Q.questionName like N'%" + subjectName + "%'"
                        + " ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("count");
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

    public int getPagesSearchDeleted(boolean isDeleted) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select count(Q.questionId) as count\n"
                        + "from tblQuestion Q, tblSubject S\n"
                        + "where Q.subjectId=S.subjectId and Q.isDeleted=? "
                        + " ";
                pst = cn.prepareStatement(sql);
                pst.setBoolean(1, isDeleted);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("count");
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

    public Map<Integer, List<QuestionDTO>> searchDeleted(boolean isDeleted, int page) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Map<Integer, List<QuestionDTO>> list = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select  Q.questionName,Q.questionId,S.subjectId,Q.createdDate,Q.isDeleted\n"
                        + "from tblQuestion Q, tblSubject S\n"
                        + "where Q.subjectId=S.subjectId and Q.isDeleted= ? "
                        + "order by S.subjectId\n"
                        + "OFFSET (? - 1)*20 ROWS FETCH NEXT 20 ROWS ONLY";
                pst = cn.prepareStatement(sql);
                pst.setBoolean(1, isDeleted);
                pst.setInt(2, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new HashMap<>();
                    }
                    String questionName = rs.getString("questionName");
                    int questionId = rs.getInt("questionId");
                    Date createdDate = rs.getDate("createdDate");
                    int subjectId = rs.getInt("subjectId");
                    List<QuestionDTO> listQues = list.get(subjectId);
                    if (listQues == null) {
                        listQues = new ArrayList<>();
                    }
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, questionName, createdDate, isDeleted);
                    listQues.add(dto);
                    list.put(subjectId, listQues);
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

    public void update(QuestionDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblQuestion set questionName=?, subjectId=?, isDeleted=? where questionId=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getQuestionName());
                pst.setInt(2, dto.getSubjectId());
                pst.setBoolean(3, dto.isIsDeleted());
                pst.setInt(4, dto.getQuestionId());
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

    public List<QuestionDTO> getRandomQuestion(int subjectId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuestionDTO> list = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select top 50 questionId,questionName,createdDate from tblQuestion\n"
                        + "where subjectId=?\n and isDeleted=0"
                        + "order by newId()";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, subjectId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    int questionId = rs.getInt("questionId");
                    String questionName = rs.getString("questionName");
                    Date createdDate = rs.getDate("createdDate");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, questionName, createdDate, false);
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

    public List<QuestionDTO> getListQuestionExam(int id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuestionDTO> list = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select subjectId,questionName,E.questionId,createdDate\n"
                        + "from tblQuestion Q , tblExamNotFinishDetail E\n"
                            + "where Q.questionId=E.questionId and E.examNotFinishId=? "
                        + "group by subjectId,questionName,E.questionId,createdDate";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                           int questionId = rs.getInt("questionId");
                    String questionName = rs.getString("questionName");
                    Date createdDate = rs.getDate("createdDate");
                    int subjectId=rs.getInt("subjectId");
                    QuestionDTO dto = new QuestionDTO(questionId, subjectId, questionName, createdDate, false);
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
