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
import java.util.Vector;

/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
public class AccountDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public AccountDB(String url, String username, String password) {
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

    public boolean addAccount(String aid, String cid, String role, String firstname, String lastname, String password) throws SQLException, IOException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        cnnct = getConnection();
        String preQueryStatement = "INSERT INTO account VALUES(?,?,?,?,?,?)";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setString(1, aid);
        pStmnt.setString(2, cid);
        pStmnt.setString(3, role);
        pStmnt.setString(4, firstname);
        pStmnt.setString(5, lastname);
        pStmnt.setString(6, password);
        int rowCount = pStmnt.executeUpdate();
        if (rowCount > 1) {
            isSuccess = true;
        }
        pStmnt.close();
        cnnct.close();
        return isSuccess;
    }

    public ArrayList<AccountBean> queryAcc() throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<AccountBean> aab = new <AccountBean> ArrayList();
        cnnct = getConnection();
        String preQueryStatement = "SELECT * FROM account";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        ResultSet rs;
        rs = pStmnt.executeQuery();
        while (rs.next()) {
            AccountBean ab = new AccountBean();
            ab.setAid(rs.getString(1));
            ab.setCid(rs.getString(2));
            ab.setRole(rs.getString(3));
            ab.setFirstName(rs.getString(4));
            ab.setLastName(rs.getString(5));
            ab.setPassword(rs.getString(6));
            aab.add(ab);
        }
        pStmnt.close();
        cnnct.close();
        return aab;
    }

    public AccountBean queryAccByAid(String aid) throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        AccountBean ab = new AccountBean();
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
            ab.setCid(rs.getString(2));
            ab.setRole(rs.getString(3));
            ab.setFirstName(rs.getString(4));
            ab.setLastName(rs.getString(5));
            ab.setPassword(rs.getString(6));
        }
        // set the record detail to the customer bean 
        pStmnt.close();
        cnnct.close();
        return ab;
    }

    public String getNewID(String role) throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        String preQueryStatement = "";
        ResultSet rs;
        cnnct = getConnection();
        String id="";
        Vector idList = new Vector();
        switch (role) {
            case "admin":
                preQueryStatement = "SELECT aid FROM `account` WHERE aid LIKE 'A%' ORDER BY `account`.`aid` ASC";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                break;
            case "teacher":
                preQueryStatement = "SELECT aid FROM `account` WHERE aid LIKE 'T%' ORDER BY `account`.`aid` ASC";
                break;
            case "student":
                preQueryStatement = "SELECT aid FROM `account` WHERE aid LIKE 'S%' ORDER BY `account`.`aid` ASC";
                break;
        }
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        rs = pStmnt.executeQuery();
        while (rs.next()) {
            idList.add(rs.getString(1));
        }
        if (!idList.isEmpty()) {
            pStmnt.close();
            cnnct.close();
            String lastId = (String) idList.lastElement();
            int lastIdNum = Integer.parseInt(lastId.substring(5)) + 1;
            id = lastId.substring(0, 5) + String.format("%05d",lastIdNum);
        } else {
        switch (role) {
            case "admin":
                id="A202000001";
                break;
            case "teacher":
                id="T202000001";
                break;
            case "student":
                id="S202000001";
                break;
        } 
        }
        return id;
    }

    public AccountBean verifyAcc(String aid, String password) throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        AccountBean ab = new AccountBean();
        boolean isSuccess = false;
        cnnct = getConnection();
        //get Connection 
        String preQueryStatement = "SELECT * FROM account WHERE aid=? AND password=?";
        //get the prepare Statement 
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        //update the placehoder with id 
        pStmnt.setString(1, aid);
        pStmnt.setString(2, password);
        ResultSet rs;
        rs = pStmnt.executeQuery();
        if (rs.next()) {
            ab.setAid(rs.getString(1));
            ab.setCid(rs.getString(2));
            ab.setRole(rs.getString(3));
            ab.setFirstName(rs.getString(4));
            ab.setLastName(rs.getString(5));
            ab.setPassword(rs.getString(6));
        }
        pStmnt.close();
        cnnct.close();
        return ab;
    }

    public boolean editAcc(AccountBean ab) throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        boolean isSuccess = false;
        cnnct = getConnection();
        String preQueryStatement = "UPDATE account SET aid = ?, cid = ?, role = ?,firstname = ?,lastname = ?, password = ? WHERE aid = ?";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setString(1, ab.getAid());
        pStmnt.setString(2, ab.getCid());
        pStmnt.setString(3, ab.getRole());
        pStmnt.setString(4, ab.getFirstName());
        pStmnt.setString(5, ab.getLastName());
        pStmnt.setString(6, ab.getPassword());
        pStmnt.setString(7, ab.getAid());
        int rowCount = pStmnt.executeUpdate();
        if (rowCount >= 1) {
            isSuccess = true;
        }
        pStmnt.close();
        cnnct.close();
        return isSuccess;
    }

}
