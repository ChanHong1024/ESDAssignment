/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AccountBean;
import ict.db.AccountDB;
import ict.bean.AttendanceBean;
import ict.db.AttendanceDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chush
 */
@WebServlet(name = "HandleTakeAttendance", urlPatterns = {"/HandleTakeAttendance"})
public class HandleTakeAttendance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private AccountDB acdb;
    private AttendanceDB attendb;
    
    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUser,dbPassword,dbUrl;
        dbUser = getServletContext().getInitParameter("dbUser");
        dbPassword = getServletContext().getInitParameter("dbPassword");
        dbUrl = getServletContext().getInitParameter("dbUrl"); 
        //2.  create a new db object  with the parameter
        acdb = new AccountDB(dbUrl,dbUser,dbPassword);
        attendb = new AttendanceDB(dbUrl,dbUser,dbPassword);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if("showAttendance".equalsIgnoreCase(action)){
            ArrayList<AccountBean> accounts;
            ArrayList<AttendanceBean> attendance;
            try {
                accounts = acdb.queryAcc();
                attendance = attendb.queryAtt();
                HttpSession session = request.getSession();
                String cid = (String)session.getAttribute("cid");
                request.setAttribute("accounts", accounts);
                request.setAttribute("attendance", attendance);
                //redirect
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/teacherAttendance.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                PrintWriter out = response.getWriter();
                out.println(ex.getMessage());
            }
        }else if ("takeAttendance".equalsIgnoreCase(action)){
            String aid = request.getParameter("aid");
            String date = request.getParameter("date");
            String status = request.getParameter("status");
            if(!attendb.editRecord(date, aid, status)){
                attendb.addRecord(date, aid, status);
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/HandleTakeAttendance?action=showAttendance&date="+date);
            rd.forward(request, response);
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
