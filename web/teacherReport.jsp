<%-- 
    Document   : teacherReport
    Created on : 2019年12月5日, 上午12:25:44
    Author     : chush
--%>

<%@page import="java.util.ArrayList"%>
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
                        <form action="teacherPrintReport.jsp">
                            <input type="text" name="cid" value="<%=cid%>" hidden/>
                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Attendance Report</h1>
                            </div>
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <div>
                                    Please choose a style:<br>
                                    <input type="radio" class="style" name="format" value="simple" checked/>Simple<br>
                                    <input type="radio" class="style" name="format" value="detail"/>With Detail
                                </div>
                                <div>
                                    Date Until:<input type="text" name="date" id="datepicker" width="276" />
                                </div>
                                <input class="btn btn-primary" type="submit" value="Generate">
                            </div>
                            <hr>
                        </form>
                        <h1 class="h3 mb-0 text-gray-800">Sample:</h1><hr>
                        <div class="simple">
                            <h1 class="h3 mb-0 text-gray-800">Attendance Until Date: xxxx-xx-xx</h1>
                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <div class="d-sm-flex align-items-center justify-content-between mb-1">
                                        <h6 class="m-0 font-weight-bold text-primary"><%=cid%></h6>
                                        <h6 class="m-0 font-weight-bold text-primary">Total School Day(s):22</h6>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Account ID</th>
                                                    <th>Name</th>
                                                    <th>Attended School Days(s)</th>
                                                    <th>Attendance Rate(%)</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Account ID</th>
                                                    <th>Name</th>
                                                    <th>Attended School Days(s)</th>
                                                    <th>Attendance Rate(%)</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <tr>
                                                    <td>180017531</td>
                                                    <td>Chan Wai Hong</td>
                                                    <td>11</td>
                                                    <td>50%</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>                
                        <!-- DataTales Example -->
                        <div class="detail" style="display:none;">
                            <h1 class="h3 mb-0 text-gray-800">Student in <%=cid%></h1>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <div class="d-sm-flex align-items-center justify-content-between mb-1">
                                        <h6 class="m-0 font-weight-bold text-primary">Account ID: 180494636</h6>
                                        <h6 class="m-0 font-weight-bold text-primary">Name: Chu Shing Fung</h6>
                                        <h6 class="m-0 font-weight-bold text-primary">Attendance Until Date: xxxx-xx-xx</h6>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table id="table" class="table table-bordered" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Total Attendance Rate(%)</th>
                                                    <th>100%</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <tr>
                                                    <td>xxxx-xx-xx</td>
                                                    <td><i class="fas fa-check"></i></td>
                                                </tr>
                                                <tr>
                                                    <td>xxxx-xx-xx</td>
                                                    <td><i class="fas fa-check"></i></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <div class="d-sm-flex align-items-center justify-content-between mb-1">
                                        <h6 class="m-0 font-weight-bold text-primary">Account ID: 180017531</h6>
                                        <h6 class="m-0 font-weight-bold text-primary">Name: Chan Wai Hong</h6>
                                        <h6 class="m-0 font-weight-bold text-primary">Attendance Until Date: xxxx-xx-xx</h6>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table id="table" class="table table-bordered" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Total Attendance Rate(%)</th>
                                                    <th>50%</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <tr>
                                                    <td>xxxx-xx-xx</td>
                                                    <td><i class="fas fa-times"></i></td>
                                                </tr>
                                                <tr>
                                                    <td>xxxx-xx-xx</td>
                                                    <td><i class="fas fa-check"></i></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
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

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd'
        });
        $(".style").click(function () {
            if ($(this).attr('value') == "simple") {
                $('.simple').show();
                $('.detail').hide();
            } else {
                $('.simple').hide();
                $('.detail').show();
            }
        });
    });
</script>