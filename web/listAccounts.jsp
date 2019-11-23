<%-- 
    Document   : listAccounts
    Created on : 2019/11/21
    Author     : Chan Wai Hong / Chu Shing Fung
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.AccountBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<AccountBean> accounts = (ArrayList<AccountBean>)request.getAttribute("accounts");
            out.println("<h1>Accounts</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Class</th><th>Account</th><th>First Name</th><th>Last Name</th><th>password</th><th>Role</th>");
            out.println("</tr>");
            for (int i = 0; i < accounts.size(); i++) {
                AccountBean a = accounts.get(i);
                out.println("<tr>");
                out.println("<td>" + a.getAid() + "</td>");
                out.println("<td>" + a.getCid() + "</td>");
                out.println("<td>" + a.getFirstName() + "</td>");
                out.println("<td>" + a.getLastName() + "</td>");
                out.println("<td>" + a.getPassword() + "</td>");
                out.println("<td>" + a.getRole() + "</td>");
                out.println("<td><a href=\"handleCustomer?action=delete&id=" + a.getAid() + "\">delete</a></td>");
                out.println("<td><a href=\"handleCustomer?action=getEditCustomer&id=" + a.getAid() + "\">edit</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
    </body>
</html>
