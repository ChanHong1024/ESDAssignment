/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.AccountBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
public class AccountDB {
    private String url="";
    private String username="";
    private String password="";
    
    public AccountDB(String url,String username,String password){
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
    
    public boolean addAccount(String aid,String cid,String role,String firstname,String lastname,String password){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO account VALUES(?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,aid);
            pStmnt.setString(2,cid);
            pStmnt.setString(3,role);
            pStmnt.setString(4,firstname);
            pStmnt.setString(5,lastname);
            pStmnt.setString(6,password);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount > 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
           ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public ArrayList<AccountBean> queryAcc() throws IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        AccountBean ab;
        ArrayList<AccountBean> aab = new <AccountBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM account";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                ab = new AccountBean();
                ab.setCid(rs.getString(1));
                ab.setAid(rs.getString(2));
                ab.setFirstName(rs.getString(3));
                ab.setLastName(rs.getString(4));
                ab.setPassword(rs.getString(5));
                ab.setRole(rs.getString(6));
                aab.add(ab);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aab;
    }
    
}
