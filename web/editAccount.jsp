<%-- 
    Document   : editCustomer
    Created on : 2019年11月21日, 上午02:51:35
    Author     : Porygon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="a" scope="request" class="ict.bean.AccountBean"/>
<%
    String type = a.getAid() != null ? "edit" : "add";
    String aid = a.getAid() != null ? a.getAid() :"";
    String cid = a.getCid() != null ? a.getCid() :"";
    String role = a.getRole() != null ? a.getRole() :"";
    String firstname = a.getFirstName() != null ? a.getFirstName() :"";
    String lastname = a.getLastName() != null ? a.getLastName() :"";
    String password = a.getPassword() != null ? a.getPassword() :"";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form  method="get" action="handleEditAccount">
            <input type="hidden" name="action"  value="<%=type%>"/>
            Aid  <input name="aid"  type="text" value="<%=aid%>"/> <br>
            Cid <input name="cid"  type="text" value="<%=cid%>"/> <br>
            Role <input name="role"  type="text" value="<%=role%>"/> <br>
            First Name <input name="firstname"  type="text" value="<%=firstname%>"/> <br>
            First Name <input name="lastname"  type="text" value="<%=lastname%>"/> <br>
            password <input name="password"  type="text" value="<%=password%>"/> <br>
            <td><input type="submit" value="submit"/> <br>
        </form>
    </body>
</html>
