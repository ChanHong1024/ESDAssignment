/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import com.mysql.jdbc.exceptions.*;
import ict.bean.AccountBean;
import ict.db.AccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Porygon
 */
@WebServlet(name = "HandleEditAccount", urlPatterns = {"/handleEditAccount"})
public class HandleEditAccount extends HttpServlet {

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

        String action = request.getParameter("action");
        //get the parameter, action, from users
        String aid,cid,role,firstname,lastname,password;
        aid = request.getParameter("aid");
        cid = request.getParameter("cid");
        role = request.getParameter("role");
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        password = request.getParameter("password");
        if ("Create".equalsIgnoreCase(action)) {
            try {
                // call the database operations
                db.addAccount(aid, cid, role, firstname, lastname, password);
                response.sendRedirect("handleAccount?action=showAll");
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
                PrintWriter out = response.getWriter();
                out.print("Duplicate primary key values");
            } catch (SQLException ex) {
                Logger.getLogger(HandleEditAccount.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                PrintWriter out = response.getWriter();
                out.print(Arrays.toString(ex.getStackTrace()));
            }
        }else if("Edit".equalsIgnoreCase(action)){
            try {
                AccountBean ab = new AccountBean(aid, cid, role, firstname, lastname, password);
                if(db.editAcc(ab)){
                    response.sendRedirect("handleAccount?action=showAll");
                }else{
                    PrintWriter out = response.getWriter();
                    out.print("False");
                }
            } catch (SQLException ex) {
                Logger.getLogger(HandleEditAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("<h1>No such action!!!</h1>");
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
