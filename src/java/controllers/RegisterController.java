/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.UserDTO;
import dtos.UserErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Validation;

/**
 *
 * @author nguye
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private final static String ERROR = "register.jsp";
    private final static String SUCESS = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String fullName = request.getParameter("txtFullName");
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");
            UserErrorDTO userError = new UserErrorDTO("", "", "", "");
            UserDAO dao = new UserDAO();
            boolean check = true;
            if (!Validation.checkEmptyAndLenght(fullName, 50)) {
                userError.setFullNameError("Full Name's length 0-50");
                check = false;
            }
            if (!Validation.checkEmptyAndLenght(email, 70)) {
                userError.setEmailError("Email's lenngh:0-70");
                check = false;
            } else if (!email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
                userError.setEmailError("Email's fornat is wrong!");
                check = false;
            }else if(dao.checkEmailDuplicate(email)){
                check=false;
                userError.setEmailError("Email's duplicate");
            }
            if (password.trim().isEmpty()) {
                userError.setPasswordError("Password can not empy");
                check = false;
            } else {
                if (rePassword.isEmpty()) {
                    userError.setRePasswordError("Re-password can not empy");
                    check = false;
                } else if (!rePassword.equals(password)) {
                    userError.setRePasswordError("Re-password is not similar");
                    check = false;
                }
            }
            if (check) {
                UserDTO user = new UserDTO(email, fullName, Validation.toHexString(Validation.getSHA256(password)), "US", "New");
                dao.insert(user);
                url = SUCESS;
            } else {
                request.setAttribute("REGISTER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Erorr at  RegisterController" + e.toString());
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
