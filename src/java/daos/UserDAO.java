/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author nguye
 */
public class UserDAO {

    public boolean checkLogin(String email, String password) throws SQLException {
        boolean result = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT fullName from tblUser where status='New' and email=? and password=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = true;
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
    public boolean checkEmailDuplicate(String email) throws SQLException{
        boolean result=false;
        Connection cn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            cn=DBUtils.getConnection();
            if(cn!=null){
                String sql="select fullName from tblUser where email=? and status='New'";
                   pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        }finally{
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
    
    public UserDTO getUser(String email, String password) throws SQLException {
        UserDTO dto = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT fullName,roleId from tblUser where status='New' and email=? and password=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String status ="New";
                    String roleId=rs.getString("roleId");
                    dto=new UserDTO(email, fullName, "", roleId, status);
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
    public void insert(UserDTO dto) throws SQLException{
        Connection cn=null;
        PreparedStatement pst=null;
        try {
            cn=DBUtils.getConnection();
            if(cn!=null){
                String sql="INSERT tblUser(fullName,email,password,status,roleId) values(?,?,?,?,?)";
                pst=cn.prepareCall(sql);
                pst.setString(1, dto.getFullName());
                pst.setString(2, dto.getEmail());
                pst.setString(3, dto.getPassword());
                pst.setString(4, dto.getStatus());
                pst.setString(5 ,dto.getRoleId());
                pst.executeUpdate();
            }
        } finally{
            if(pst!=null){
                pst.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
    }
}
