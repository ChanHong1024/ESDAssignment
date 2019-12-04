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

    private String url = "";
    private String username = "";
    private String password = "";

    public ClassDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

    public ArrayList<ClassBean> queryClass() {
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<ClassBean> acb = new <ClassBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT class.cid,className,count(account.cid) AS \"population\"\n"
                    + "FROM class\n"
                    + "LEFT JOIN account ON class.cid = account.cid\n"
                    + "GROUP BY class.cid;";
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

    public ClassBean queryClassByCid(String cid) {
        Connection cnnct;
        PreparedStatement pStmnt;
        ClassBean ab = new ClassBean();
        try {
            cnnct = getConnection();
            //get Connection 
            String preQueryStatement = "SELECT * FROM class WHERE cid=?";
            //get the prepare Statement 
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //update the placehoder with id 
            pStmnt.setString(1, cid);
            ResultSet rs;
            rs = pStmnt.executeQuery();
            //execute the query and assign to the result 
            if (rs.next()) {
                ab.setCid(rs.getString(1));
                ab.setClassName(rs.getString(2));
            }
            // set the record detail to the customer bean 
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ab;
    }

    public boolean addClass(String cid, String className) throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        boolean isSuccess = false;
        cnnct = getConnection();
        String preQueryStatement = "INSERT INTO class VALUES(?,?)";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setString(1, cid);
        pStmnt.setString(2, className);
        int rowCount = pStmnt.executeUpdate();
        if (rowCount > 1) {
            isSuccess = true;
        }
        pStmnt.close();
        cnnct.close();
        return isSuccess;
    }

    public boolean editClass(ClassBean cb) {
        Connection cnnct;
        PreparedStatement pStmnt;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE class SET cid = ?, className = ? WHERE cid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getCid());
            pStmnt.setString(2, cb.getClassName());
            pStmnt.setString(3, cb.getCid());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

}
