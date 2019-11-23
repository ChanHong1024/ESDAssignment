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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
@WebServlet(name = "HandleAccount", urlPatterns = {"/HandleAccount"})
public class HandleAccount extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("showAllAccounts".equalsIgnoreCase(action)) {
           // call the query db to get retrieve for all account 
            ArrayList<AccountBean> accounts = db.queryAcc();
        // set the result into the attribute	 
            request.setAttribute("accounts",accounts);
        // redirect the result to the listAccounts.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listAccounts.jsp");
            rd.forward(request, response);
        }else {
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
