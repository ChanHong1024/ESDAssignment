/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AccountBean;
import ict.bean.ClassBean;
import ict.db.AccountDB;
import ict.db.ClassDB;
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
@WebServlet(name = "HandleClass", urlPatterns = {"/handleClass"})
public class HandleClass extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ClassDB db;
    
    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        String dbUser,dbPassword,dbUrl;
        dbUser = getServletContext().getInitParameter("dbUser");
        dbPassword = getServletContext().getInitParameter("dbPassword");
        dbUrl = getServletContext().getInitParameter("dbUrl"); 
        //2.  create a new db object  with the parameter
        db = new ClassDB(dbUrl,dbUser,dbPassword);
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("showAll".equalsIgnoreCase(action)) {
            ArrayList<ClassBean> classes = db.queryClass(); 
            request.setAttribute("classes", classes);
            //redirect
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listClass.jsp");
            rd.forward(request, response);
        }if("printAllClass".equalsIgnoreCase(action)) {
            ArrayList<ClassBean> classes = db.queryClass(); 
            PrintWriter out = response.getWriter();
            for(int i=0;i<classes.size();i++){
                out.print(classes.get(i).getCid());
                if(i < classes.size()-1){
                    out.print(",");
                }
            }
        }else if("getClassByCid".equalsIgnoreCase(action)){
            String cid = request.getParameter("cid");
            ClassBean classes = db.queryClassByCid(cid);	 
            request.setAttribute("c", classes);
        // redirect to the result
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/editClass.jsp");
            rd.forward(request, response);
        }else{
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
