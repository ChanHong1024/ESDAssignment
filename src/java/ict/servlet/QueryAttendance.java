/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;
import ict.db.AccountDB;
import ict.bean.AccountBean;
/**
 *
 * @author chush
 */
public class QueryAttendance {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/esdassignment";
        String username = "root";
        String password = "";
        AccountDB custDb = new AccountDB(url, username, password);
        AccountBean cb = custDb.queryCustByID("1");
        System.out.println(cb.toString());
    }
}
