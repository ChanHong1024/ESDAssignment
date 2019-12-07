/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.SchoolDayDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Porygon
 */
@WebServlet(name = "HandleTimeTable", urlPatterns = {"/handleTimeTable"})
public class HandleTimeTable extends HttpServlet {

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
        String dbUser,dbPassword,dbUrl;
        dbUser = getServletContext().getInitParameter("dbUser");
        dbPassword = getServletContext().getInitParameter("dbPassword");
        dbUrl = getServletContext().getInitParameter("dbUrl"); 
        //2.  create a new db object  with the parameter
        db = new SchoolDayDB(dbUrl,dbUser,dbPassword);
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String cid = request.getParameter("cid");
        ArrayList<String> dates = db.querySchoolDayByCid(cid);
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<dates.size();i++){
            Map map = new HashMap();
            map.put("title", "School Day (Click to revoke)");
            map.put("start", dates.get(i));
            map.put("url", "javascript:delModal('"+cid+"','"+dates.get(i)+"');");
            jsonArray.put(new JSONObject(map));
        }
        out.print(jsonArray.toString());
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
