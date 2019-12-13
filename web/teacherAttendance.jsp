<%-- 
    Document   : listAccounts
    Created on : 2019/11/21
    Author     : Chan Wai Hong / Chu Shing Fung
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.AccountBean"%>
<%@page import="ict.bean.AttendanceBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String roleSession = (String) session.getAttribute("role");
    if (session.getAttribute("isLoggedIn") == null || !(roleSession.equalsIgnoreCase("teacher"))) {
        response.sendRedirect("login.jsp");
    }
    String firstname = (String) session.getAttribute("firstname");
    String lastname = (String) session.getAttribute("lastname");
    String cid = (String) session.getAttribute("cid");
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <!-- Custom fonts-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <!-- Custom styles-->
        <link href="css/style.css" rel="stylesheet">
        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link rel="icon" href="img/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-danger sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="login.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-check-circle"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Attendence<sup>2</sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    My Class
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ClassCollapse" aria-expanded="true" aria-controls="ClassCollapse">
                        <i class="fas fa-users"></i>
                        <span><%=cid%></span>
                    </a>
                    <div id="ClassCollapse" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Class Control:</h6>
                            <a class="collapse-item" href="HandleStudentList">Student List</a>
                            <a class="collapse-item" href="teacherTimeTable.jsp">School Day</a>
                        </div>
                    </div>
                </li>  

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Functions
                </div>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="teacherReport.jsp">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Attendance Report</span></a>
                </li>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="HandleTakeAttendance?action=showAttendance">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Take Attendance</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Search -->
                        <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                            <li class="nav-item dropdown no-arrow d-sm-none">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=firstname + " " + lastname%></span>
                                    <img class="img-profile rounded-circle" src="img/icon.jpg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Settings
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Activity Log
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Attendance System</h1>
                            <a href="#" id="printBtn" onclick="printDiv();" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Attendance Sheet</a>
                            <input class="form-control" type="text" id="datepicker" width="276" readonly/>
                        </div>
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"><%=request.getParameter("date") != null ? request.getParameter("date") : "Please select a date"%></h6>
                            </div>
                            <div class="card-body">
                                <div id="table" class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Account ID</th>
                                                <th>Name</th>
                                                <th>Attendance</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <jsp:useBean id="accounts" class="ArrayList<AccountBean>" scope="request" />
                                            <jsp:useBean id="attendance" class="ArrayList<AttendanceBean>" scope="request" />
                                            <%
                                                boolean notAttended = false;
                                                boolean isSchoolDay = false;
                                                ArrayList<String> schoolDays = (ArrayList<String>) request.getAttribute("schoolDays");
                                                String date = request.getParameter("date") != null ? request.getParameter("date") : "fail";
                                                for (int i = 0; i < schoolDays.size(); i++) {
                                                    if (date.equals(schoolDays.get(i))) {
                                                        isSchoolDay = true;
                                                        break;
                                                    }
                                                }
                                                if (date.equals("fail")) {
                                                    isSchoolDay = true;
                                                }
                                                if (isSchoolDay) {
                                                    for (int i = 0; i < accounts.size(); i++) {
                                                        AccountBean a = accounts.get(i);
                                                        String aCid = a.getCid() != null ? a.getCid() : "";
                                                        if (!(aCid.equals(cid)) || a.getRole().equals("teacher")) {
                                                            continue;
                                                        }
                                                        out.println("<tr>");
                                                        out.println("<td class='aid'>" + a.getAid() + "</td>");
                                                        out.println("<td>" + a.getLastName() + a.getFirstName() + "</td>");
                                                        out.println("<td>");
                                                        if (!date.equals("fail")) {
                                                            for (int n = 0; n < attendance.size(); n++) {
                                                                if (attendance.get(n).getAid().equals(a.getAid()) && attendance.get(n).getDate().equals(date) && attendance.get(n).getStatus()) {
                                                                    out.println("<button class='btn_attendance form-control'>Attended</button>");
                                                                    notAttended = false;
                                                                    break;
                                                                } else {
                                                                    notAttended = true;
                                                                }
                                                            }
                                                            if (notAttended) {
                                                                out.println("<button class='btn_attendance form-control'>Not Attended</button>");
                                                            }
                                                        }
                                                        out.println("</td>");
                                                        out.println("</tr>");
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Chan Wai Hong / Chu Shing Fung 2019</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="handleLogout">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/script.min.js"></script>


<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd'
        });

        $('#datepicker').change(function () {
            $.getJSON('handleTimeTable?cid=<%=cid%>', function (data) {
                var isInvaild = false;
                $.each(data, function (index, d) {
                    if (d.start === $('#datepicker').val()) {
                        isInvaild = true;
                    }
                });

                if (!isInvaild) {
                    alert("not a vaild selection!!", "warning");
                    window.location.href = "HandleTakeAttendance?action=takeAttendance&date=" + $('#datepicker').val();
                } else {
                    window.location.href = "HandleTakeAttendance?action=takeAttendance&date=" + $('#datepicker').val();
                }
            });
        });

        $('.btn_attendance').click(function () {
            var status;
            var date = getUrlParameter('date');
            if ($(this).text() === "Attended") {
                status = 0;
            } else {
                status = 1;
            }
            window.location.href = "HandleTakeAttendance?action=takeAttendance&date=" + date + "&aid=" + $(this).closest('tr').find("td:eq(0)").text() + "&status=" + status;
        });
        function getUrlParameter(sParam) {
            var sPageURL = window.location.search.substring(1),
                    sURLVariables = sPageURL.split('&'),
                    sParameterName,
                    i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
                }
            }
        };
    });
</script>
<script>
    function printDiv() {
        var divContents = document.getElementById("table").innerHTML;
        divContents = divContents.replace('<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">', '<table border="1" width="100%">');
        var a = window.open('', '', 'height=500, width=1000');
        a.document.write('<html>');
        a.document.write('<body style="text-align: center;"> <h1>Attendance Sheet of Class: <%=cid%> </h1>Date:________________<br>');
        a.document.write(divContents);
        a.document.write('</table></body></html>');
        a.document.close();
        a.print();
    }
</script>