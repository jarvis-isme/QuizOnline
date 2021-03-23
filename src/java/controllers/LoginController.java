/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Validation;

/**
 *
 * @author nguye
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private final static String ERROR = "index.jsp";
    private final static String SUCCESS = "ShowHomePageController";
    private final static String ADMIN = "SearchQuestionController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            if (email != null && password != null) {
                UserDAO dao = new UserDAO();
                boolean result = dao.checkLogin(email, Validation.toHexString(Validation.getSHA256(password)));
                if (result) {
                    UserDTO user = dao.getUser(email,  Validation.toHexString(Validation.getSHA256(password)));
                    if (user != null) {
                        if (!user.getRoleId().equals("US") && !user.getRoleId().equals("AD")) {
                            request.setAttribute("LOGIN_ERROR", "Your role is not valid ");
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("USER", user);
                            if (user.getRoleId().equals("AD")) {
                                url = ADMIN;
                            } else {
                                url = SUCCESS;
                            }
                        }
                    }
                } else {
                    request.setAttribute("LOGIN_ERROR", "Email/Passwors is wrong");
                }
            }
        } catch (Exception e) {
            log("Error at LoginController:" + e.toString());
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
