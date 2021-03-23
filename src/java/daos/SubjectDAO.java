/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.SubjectDTO;
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
public class SubjectDAO {

    public List<SubjectDTO> getSubjectss() throws SQLException {
        List<SubjectDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select subjectId,subjectName from tblSubject ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    int subjectId = rs.getInt("subjectId");
                    String subjectName = rs.getString("subjectName");
                    SubjectDTO dto = new SubjectDTO(subjectId, subjectName);
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
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public List<SubjectDTO> getSubjectsExam1() throws SQLException {
        List<SubjectDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select count(Q.subjectId) as count ,Q.subjectId, S.subjectName\n"
                        + "from tblQuestion Q join  tblSubject S on Q.subjectId=S.subjectId \n"
                        + "group by Q.subjectId,S.subjectName,Q.isDeleted\n"
                        + "having Q.isDeleted=0";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int count=rs.getInt("count");
                    if(count>=5){
                        if (list == null) {
                        list = new ArrayList<>();
                    }
                        int subjectId = rs.getInt("subjectId");
                        String subjectName = rs.getString("subjectName");
                        SubjectDTO dto = new SubjectDTO(subjectId, subjectName);
                        list.add(dto);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();

            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }
}
