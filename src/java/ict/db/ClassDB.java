/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;
import ict.bean.ClassBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
public class ClassDB {
    private String url="";
    private String username="";
    private String password="";
    
    public ClassDB(String url,String username,String password){
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
 
    
    public ArrayList<ClassBean> queryClass() throws IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<ClassBean> acb = new <ClassBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT class.cid,className,count(account.cid) AS population FROM class,account WHERE role != \"teacher\" AND class.cid=account.cid GROUP BY class.cid ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                ClassBean cb = new ClassBean();
                cb.setCid(rs.getString(1));
                cb.setClassName(rs.getString(2));
                cb.setPopulation(rs.getInt(3));
                acb.add(cb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return acb;
    }
    
}
