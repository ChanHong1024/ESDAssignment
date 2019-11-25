/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;
import ict.bean.SchoolDayBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author chush
 */
public class SchoolDayDB {
        private String url="";
    private String username="";
    private String password="";
    
    public SchoolDayDB(String url,String username,String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
   
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
    }
        return DriverManager.getConnection(url,username,password);
    }
    
    public ArrayList<String> querySchoolDayByCid(String cid){
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<String> sdba = new <String> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM schoolday WHERE cid = '"+cid+"'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                sdba.add(rs.getString(1));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return sdba;  
    } 
}
