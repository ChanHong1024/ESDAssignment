/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.SchoolDayBean;
import ict.db.SchoolDayDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "HandleSD", urlPatterns = {"/handleSD"})
public class HandleSD extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private SchoolDayDB db;

    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUser, dbPassword, dbUrl;
        dbUser = getServletContext().getInitParameter("dbUser");
        dbPassword = getServletContext().getInitParameter("dbPassword");
        dbUrl = getServletContext().getInitParameter("dbUrl");
        //2.  create a new db object  with the parameter
        db = new SchoolDayDB(dbUrl, dbUser, dbPassword);
    }

    
    public Date conDate(String dateStr){
        java.sql.Date sqlDate = new Date(0);
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf1.parse(dateStr); 
            sqlDate = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(HandleSD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("Schedule".equalsIgnoreCase(action)) {
            String cid = request.getParameter("cid");
            Date date = conDate(request.getParameter("date"));
            // call the database operations
            db.addSD(cid, date);
            PrintWriter out = response.getWriter();
            out.println("dgdfgdf" + date);
            response.sendRedirect("timeTable.jsp");
        } else if ("Delete".equalsIgnoreCase(action)) {
            String cid = request.getParameter("cid");
            Date date = conDate(request.getParameter("date"));
            SchoolDayBean sb = new SchoolDayBean(cid,date);
            if(db.deleteSD(sb)){
                PrintWriter out = response.getWriter();
                out.print("Success");
            }else{
                PrintWriter out = response.getWriter();
                out.print("Fail");
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
