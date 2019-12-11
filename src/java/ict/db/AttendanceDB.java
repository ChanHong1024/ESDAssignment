/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.AttendanceBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
public class AttendanceDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public AttendanceDB(String url, String username, String password) {
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

    public ArrayList<AttendanceBean> queryAttByAid(String aid) {
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<AttendanceBean> aab = new <AttendanceBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM attendance WHERE aid = '" + aid + "'";
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

    public ArrayList<AttendanceBean> queryAtt() {
        Connection cnnct;
        PreparedStatement pStmnt;
        ArrayList<AttendanceBean> aab = new <AttendanceBean> ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM attendance";
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

    public boolean addRecord(String date, String aid, String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO attendance VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, date);
            pStmnt.setString(2, aid);
            pStmnt.setString(3, status);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
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
        return isSuccess;
    }

    public boolean editRecord(String date, String aid, String status) {
        Connection cnnct;
        PreparedStatement pStmnt;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE attendance "
                    + "SET date = ?, aid = ?, status = ? "
                    + "WHERE aid = ?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, date);
            pStmnt.setString(2, aid);
            pStmnt.setString(3, status);
            pStmnt.setString(4, aid);
            System.out.println(pStmnt);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
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
        return isSuccess;
    }

    public int getTotal(String aid,String cid) throws SQLException, IOException {
        Connection cnnct;
        PreparedStatement pStmnt;
        int count,sum;
        cnnct = getConnection();
        String preQueryStatement = "SELECT count(*) FROM `attendance` WHERE aid=\"?\" and status = true";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setString(1, aid);
        System.out.println(pStmnt);
        ResultSet rs;
        rs = pStmnt.executeQuery();
        count =  rs.getInt(1);
        preQueryStatement = "SELECT count(*) FROM `schoolday` WHERE cid=\"?\"";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setString(1, cid);
        System.out.println(pStmnt);
        rs = pStmnt.executeQuery();
        sum =  rs.getInt(1);
        pStmnt.close();
        cnnct.close();
        return (count/sum)*100;
    }
}
