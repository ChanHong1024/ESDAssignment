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
import ict.db.AttendanceDB;

/**
 *
 * @author chush
 */
public class attendanceTag extends SimpleTagSupport {
    String cid;
    String date;
    String tagType;
    
    public void setCid(String cid) { this.cid = cid; }
    public void setDate(String date) { this.date = date; }
    public void setTagType(String tagType) { this.tagType = tagType; }
    public void doTag() {
        /*
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            if ("simple".equalsIgnoreCase(tagType)) {
                out.print("Prime number between " + min + " and " + max + " are ");
                for (int i = min; i <= max; i++) {  
                    if (isPrime(i)) {  
                       out.print(i + ", ");
                    }
                }
            } else if ("table".equalsIgnoreCase( tagType)) {
                out.print("Prime number between " + min + " and " + max + " are follows:<br><table><tr>");
                for (int i = min; i <= max; i++) {  
                    if (isPrime(i)) {  
                       out.print("<td>" + i + "</td>");
                       if(i % 10 == 0){
                           out.print("</tr><tr>");
                       }
                    }
                }
                out.print("</tr></table>");
            } else {
                out.println("No such type");
        }
        } catch (IOException ioe) {
            System.out.println("Error generating prime: " + ioe);
        }
        */
    }


    public static boolean isPrime(int n) {  
       if (n <= 1) {  
           return false;  
       }  
       for (int i = 2; i <= Math.sqrt(n); i++) {  
           if (n % i == 0) {  
               return false;  
           }  
       }  
       return true;  
   }  
}