/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.AccountBean;
import ict.bean.AttendanceBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
public class AttendanceDB {
    private String url="";
    private String username="";
    private String password="";
    
    public AttendanceDB(String url,String username,String password){
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
    
   public ArrayList<AttendanceBean> queryAttByAid(String aid){
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<AttendanceBean> aab = new <AttendanceBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM attendance WHERE aid = '"+aid+"'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                AttendanceBean ab = new AttendanceBean();
                ab.setDate(rs.getString(1));
                ab.setAid(rs.getString(2));
                ab.setStatus(rs.getBoolean(3));
                aab.add(ab);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return aab;  
    }
    
    public boolean addAccount(String aid,String role,String firstname,String lastname,String password){
        Connection cnnct;
        PreparedStatement pStmnt;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO account VALUES(?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,aid);
            pStmnt.setString(2,role);
            pStmnt.setString(3,firstname);
            pStmnt.setString(4,lastname);
            pStmnt.setString(5,password);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount > 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException | IOException ex){
           ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public ArrayList<AccountBean> queryAcc() throws IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<AccountBean> aab = new <AccountBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM account";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                AccountBean ab = new AccountBean();
                ab.setAid(rs.getString(1));
                ab.setRole(rs.getString(2));
                ab.setFirstName(rs.getString(3));
                ab.setLastName(rs.getString(4));
                ab.setPassword(rs.getString(5));
                aab.add(ab);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return aab;
    }
    
        public AccountBean queryAccByAid(String aid){
        Connection cnnct;
        PreparedStatement pStmnt; 
        AccountBean ab = new AccountBean();
        try {
            cnnct = getConnection();
            //get Connection 
            String preQueryStatement = "SELECT * FROM account WHERE aid=?"; 
            //get the prepare Statement 
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //update the placehoder with id 
            pStmnt.setString(1, aid);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            //execute the query and assign to the result 
            if (rs.next()) {
                ab.setAid(rs.getString(1));
                ab.setRole(rs.getString(2));
                ab.setFirstName(rs.getString(3));
                ab.setLastName(rs.getString(4));
                ab.setPassword(rs.getString(5));
            } 
            // set the record detail to the customer bean 
            pStmnt.close(); 
            cnnct.close(); 
        } catch (SQLException ex){
            while (ex != null) { 
                ex.printStackTrace();
                ex = ex.getNextException();
                }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return ab; 
    }      
}
