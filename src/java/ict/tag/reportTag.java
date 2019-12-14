/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import javax.servlet.jsp.*;
import java.io.*;
import javax.servlet.jsp.tagext.SimpleTagSupport; 
import ict.db.AttendanceDB;
import ict.db.SchoolDayDB;
import ict.db.AccountDB;
import ict.bean.AccountBean;
import ict.bean.AttendanceBean;
import java.util.ArrayList;

/**
 *
 * @author chush
 */
public class reportTag extends SimpleTagSupport {
    String date;
    String cid;
    String format;
    
    public void setDate(String date) { this.date = date; }
    public void setCid(String cid) { this.cid = cid; }
    public void setFormat(String format) { this.format = format; }
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            AccountDB acdb = new AccountDB("jdbc:mysql://localhost:3306/esdassignment","root","");
            SchoolDayDB sddb = new SchoolDayDB("jdbc:mysql://localhost:3306/esdassignment","root","");
            AttendanceDB attendb = new AttendanceDB("jdbc:mysql://localhost:3306/esdassignment","root","");
            ArrayList<AccountBean> accounts;
            ArrayList<AttendanceBean> attendance;
            ArrayList<String> schoolDays;
            accounts = acdb.queryAcc();
            attendance = attendb.queryAtt();
            schoolDays = sddb.querySchoolDayByCidDate(cid,date);
            if ("simple".equalsIgnoreCase( format)) {
                out.println("<h1 class='h3 mb-0 text-gray-800'>Attendance Until Date: "+date+"</h1>\n" +
"                        <!-- DataTales Example -->\n" +
"                        <div class='card shadow mb-4 simple'>\n" +
"                            <div class='card-header py-3'>\n" +
"                                <div class='d-sm-flex align-items-center justify-content-between mb-1'>\n" +
"                                    <h6 class='m-0 font-weight-bold text-primary'>"+cid+"</h6>\n" +
"                                    <h6 class='m-0 font-weight-bold text-primary'>Total School Day(s):"+schoolDays.size()+"</h6>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                            <div class='card-body'>\n" +
"                                <div class='table-responsive'>\n" +
"                                    <table class='table table-bordered' id='dataTable' width='100%' cellspacing='0'>\n" +
"                                        <thead>\n" +
"                                            <tr>\n" +
"                                                <th>Account ID</th>\n" +
"                                                <th>Name</th>\n" +
"                                                <th>Attended School Days(s)</th>\n" +
"                                                <th>Attendance Rate(%)</th>\n" +
"                                            </tr>\n" +
"                                        </thead>\n" +
"                                        <tfoot>\n" +
"                                            <tr>\n" +
"                                                <th>Account ID</th>\n" +
"                                                <th>Name</th>\n" +
"                                                <th>Attended School Days(s)</th>\n" +
"                                                <th>Attendance Rate(%)</th>\n" +
"                                            </tr>\n" +
"                                        </tfoot>\n" +
"                                        <tbody>");
                for (int i = 0; i < accounts.size(); i++) {
                    String beforeCal;
                    AccountBean a = accounts.get(i);
                    int attendedDays = 0;
                    String aCid = a.getCid() !=null ? a.getCid():"";
                    String aRole = a.getRole();
                    if (!(aCid.equals(cid)) || aRole.equals("teacher")) {
                        continue;
                    }
                    beforeCal = "<tr>";
                    beforeCal += "<td>" + a.getAid() + "</td>";
                    beforeCal += "<td>" + a.getLastName() + a.getFirstName() + "</td>";
                    beforeCal += "<td>";
                    for (int n = 0; n < attendance.size(); n++) {
                        AttendanceBean atten = attendance.get(n);
                        for (int d = 0; d < schoolDays.size(); d++) {
                            if (atten.getAid().equals(a.getAid()) && atten.getDate().equals(schoolDays.get(d)) && atten.getStatus()) {
                                attendedDays++;
                                break;
                            }
                        }
                    }
                    if(!schoolDays.isEmpty()){
                        if((attendedDays * 100 / schoolDays.size())>60){
                            continue;
                        }
                    }
                    out.println(beforeCal + attendedDays + "</td>");
                    out.println("<td>");
                    if (schoolDays.isEmpty()) {
                        out.println("No School Day</td>");
                    } else {
                        out.println((attendedDays * 100 / schoolDays.size()) + "%</td>");
                    }
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>\n" +
"                                    </table>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>");
            } else if ("detail".equalsIgnoreCase( format)) {
                out.println("<h1 class='h3 mb-0 text-gray-800'>Student in "+cid+"</h1>");
                for(int i = 0; i < accounts.size(); i++){
                    String beforeCal;
                    AccountBean a = accounts.get(i);
                    int daysAttended = 0;
                    String aCid = a.getCid() !=null ? a.getCid():"";
                    String aRole = a.getRole();
                    if (!(aCid.equals(cid)) || aRole.equals("teacher")) {
                        continue;
                    }
                    beforeCal = "<div class='card shadow mb-4'>\n" +
    "                                <div class='card-header py-3'>\n" +
    "                                    <div class='d-sm-flex align-items-center justify-content-between mb-1'>\n" +
    "                                        <h6 class='m-0 font-weight-bold text-primary'>Account ID: "+a.getAid()+"</h6>\n" +
    "                                        <h6 class='m-0 font-weight-bold text-primary'>Name: "+a.getLastName()+" "+a.getFirstName()+"</h6>\n" +
    "                                        <h6 class='m-0 font-weight-bold text-primary'>Attendance Until Date: "+date+"</h6>\n" +
    "                                    </div>\n" +
    "                                </div>\n" +
    "                                <div class='card-body'>\n" +
    "                                    <div class='table-responsive'>\n" +
    "                                        <table id='table' class='table table-bordered' width='100%' cellspacing='0'>\n" +
    "                                            <thead>\n" +
    "                                                <tr>\n" +
    "                                                    <th>Date</th>\n" +
    "                                                    <th>Status</th>\n" +
    "                                                </tr>\n" +
    "                                            </thead>\n" +
    "                                            <tbody>\n";
                                                attendance = attendb.queryAttByAid(a.getAid());
                                                for(int n = 0; n < attendance.size(); n++){
                                                    beforeCal += "<tr><td>"+attendance.get(n).getDate()+"</td>";
                                                    if(attendance.get(n).getStatus()){
                                                        daysAttended++;
                                                        beforeCal += "<td><i class=\"fas fa-check\"></i></td></tr>";
                                                    }else{
                                                        beforeCal += "<td><i class=\"fas fa-times\"></i></td></tr>";
                                                    }
                                                }
                                                beforeCal += "<tr><th>School Days Attended/Total School Day(s)</th><th>"+daysAttended+"/"+schoolDays.size()+"</th></tr>";
                                                if(schoolDays.isEmpty()){
                                                    beforeCal += "<tr><th>Total Attendance Rate(%)</th><th>No School Day</th></tr>";
                                                }else{
                                                    beforeCal += "<tr><th>Total Attendance Rate(%)</th><th>"+(daysAttended*100/schoolDays.size())+"%</th></tr>";
                                                    if((daysAttended*100/schoolDays.size())>=60){
                                                        continue;
                                                    }
                                                }                                                  
                                                out.println(beforeCal+"</tbody>\n" +
    "                                        </table>\n" +
    "                                    </div>\n" +
    "                                </div>\n" +
    "                            </div>\n" +
    "                            <hr>");
                }
            } else {
                out.println("No such type");
        }
        } catch (IOException ioe) {
            System.out.println("Error generating report: " + ioe);
        } catch (Exception e) {
            System.out.println("Unexpected error generating report: " + e);
        } 
    }
}
