/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AccountBean;
import ict.db.AccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Porygon
 */
@WebServlet(name = "HandleLogin", urlPatterns = {"/handleLogin"})
public class HandleLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private AccountDB db;
    
    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUser,dbPassword,dbUrl;
        dbUser = getServletContext().getInitParameter("dbUser");
        dbPassword = getServletContext().getInitParameter("dbPassword");
        dbUrl = getServletContext().getInitParameter("dbUrl"); 
        //2.  create a new db object  with the parameter
        db = new AccountDB(dbUrl,dbUser,dbPassword);
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String aid = request.getParameter("aid");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            AccountBean ab = db.verifyAcc(aid, password);
            if(ab.getAid()!= null){
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("firstname", ab.getFirstName());
                session.setAttribute("lastname", ab.getLastName());
                session.setAttribute("aid", ab.getAid());
                session.setAttribute("cid", ab.getCid());
                session.setAttribute("role", ab.getRole());
                if(ab.getRole().equalsIgnoreCase("student")){
                    response.sendRedirect("handleAttendance?action=showMyAtt");
                }else if(ab.getRole().equalsIgnoreCase("admin")){
                    response.sendRedirect("adminIndex.jsp");
                }else if(ab.getRole().equalsIgnoreCase("teacher")){
                    response.sendRedirect("teacherIndex.jsp");
                }
            }else{
                PrintWriter out = response.getWriter();
                out.println("<h1>No such action!!!</h1>");
                response.sendRedirect("login.jsp?v=false");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HandleLogin.class.getName()).log(Level.SEVERE, null, ex);
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
