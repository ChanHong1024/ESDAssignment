<%-- 
    Document   : login
    Created on : 2019年11月24日, 下午07:25:47
    Author     : Porygon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role;
    if (session.getAttribute("role")!=null){
        role = (String)session.getAttribute("role");
    if (role.equalsIgnoreCase("student")) {
            response.sendRedirect("handleAttendance?action=showMyAtt");
        } else if (role.equalsIgnoreCase("admin")) {
            response.sendRedirect("adminIndex.jsp");
        } else if (role.equalsIgnoreCase("teacher")) {
            response.sendRedirect("teacherIndex.jsp");
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Attendence - Login</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/style.css" rel="stylesheet">

    </head>

    <body class="bg-gradient-primary">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                        </div>
                                        <form class="user" method="post" action="handleLogin">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-user" name="aid" id="aid" aria-describedby="emailHelp" placeholder="Enter Account ID">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user" name="password" id="password" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <div class="custom-control custom-checkbox small">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck">
                                                    <label class="custom-control-label" for="customCheck">Remember Me</label>
                                                </div>
                                            </div>
                                            <input type="submit" value="submit"  class="btn btn-primary btn-user btn-block"/>
                                            <hr>
                                        </form>
                                        <%
                                            if (request.getParameter("v") != null && request.getParameter("v").equalsIgnoreCase("false")) {
                                                out.println("<div class='text-center'><p style='color:red;'>Account or Password incorrect!</p></div>");
                                            }
                                        %>
                                        <hr>
                                        <div class="text-center">
                                            <a class="small" href="forgot-password.html">Forgot Password?</a>
                                        </div>
                                        <div class="text-center">
                                            <a class="small" href="register.html">Create an Account!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/script.js"></script>

    </body>
</html>
