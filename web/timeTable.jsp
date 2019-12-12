<%-- 
    Document   : editCustomer
    Created on : 2019年11月21日, 上午02:51:35
    Author     : Porygon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="c" scope="request" class="ict.bean.ClassBean"/>
<%
    String roleSession = (String) session.getAttribute("role");
    if (session.getAttribute("isLoggedIn") == null || !(roleSession.equalsIgnoreCase("admin"))) {
        response.sendRedirect("login.jsp");
    }
    String fname = (String) session.getAttribute("firstname");
    String lname = (String) session.getAttribute("lastname");
%>
<html>
    <head>
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="css/style.css" rel="stylesheet">
        <link rel="icon" href="img/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Custom css for calendar-->
        <link href='vendor/core/main.css' rel='stylesheet' />
        <link href='vendor/daygrid/main.css' rel='stylesheet' />
        <link href='vendor/timegrid/main.css' rel='stylesheet' />
        <link href='vendor/list/main.css' rel='stylesheet' />
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="adminIndex.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-check-circle"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Attendence<sup>2</sup></div>
      </a>


      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Student Affairs
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-user-alt"></i>
          <span>Account</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Account Control:</h6>
            <a class="collapse-item" href="editAccount.jsp">Create Account</a>
            <a class="collapse-item" href="handleAccount?action=showAll">List Account</a>
          </div>
        </div>
      </li>
      
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ClassCollapse" aria-expanded="true" aria-controls="ClassCollapse">
          <i class="fas fa-users"></i>
          <span>Class</span>
        </a>
        <div id="ClassCollapse" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Class Control:</h6>
            <a class="collapse-item" href="editClass.jsp">Create Class</a>
            <a class="collapse-item" href="handleClass?action=showAll">List Class</a>
          </div>
        </div>
      </li>  
      

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        School Affairs 
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#SchoolDayCollapse" aria-expanded="true" aria-controls="SchoolDayCollapse">
          <i class="fas fa-fw fa-calendar-alt"></i>
          <span>School Day</span>
        </a>
        <div id="SchoolDayCollapse" class="collapse" aria-labelledby="SchoolDayCollapse" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Class Control:</h6>
            <a class="collapse-item" href="editSD.jsp">Schedule School Day</a>
            <a class="collapse-item" href="timeTable.jsp">Timetable for each class</a>
          </div>
        </div>
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
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=fname + " " + lname%></span>
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
                            <h1 class="h3 mb-0 text-gray-800">Time Table</h1>
                            <div>
                                <a href="editSD.jsp" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-user-alt"></i> Schedule School Day</a>

                            </div>
                        </div>
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-sm-flex align-items-center justify-content-between mb-4">
                                <h6 class="m-0 font-weight-bold text-primary">Class Time Table</h6>
                                <select id="classSelect" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                </select>
                            </div>
                            <div class="card-body">
                                <div id='calendar'></div>
                                <div id='loading'>loading...</div>
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

        <!-- Delete Date Modal-->
        <div class="modal fade" id="deleteDateModal" tabindex="-1" role="dialog" aria-labelledby="deleteDateModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteDateModalLabel">Ready to Revoke Schedule?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <label for="delCid">Class </label>
                        <input class="form-control" id="delCid" type="text" readonly/>
                        <hr>
                        <label for="delCid">Date </label>
                        <input class="form-control" id="delDate" type="text" readonly/>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-danger" href="javascript:delSchedule()">Revoke Schedule</a>
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
<script src="js/script.js"></script> 
<!-- Custom scripts for calendar-->
<link href='vendor/core/main.css' rel='stylesheet' />
<link href='vendor/daygrid/main.css' rel='stylesheet' />
<link href='vendor/timegrid/main.css' rel='stylesheet' />
<link href='vendor/list/main.css' rel='stylesheet' />
<script src='vendor/core/main.js'></script>
<script src='vendor/interaction/main.js'></script>
<script src='vendor/daygrid/main.js'></script>
<script src='vendor/timegrid/main.js'></script>
<script src='vendor/list/main.js'></script>
<script src='vendor/moment/main.js'></script>
<script>
    var calendar;

    function addSelect(value) {
        $("#classSelect").append(new Option(value, value, false));
    }

    function delModal(cid, date) {
        $("#delCid").val(cid);
        $("#delDate").val(date);
        $("#deleteDateModal").modal();
    }

    function delSchedule() {

        var cid = $("#delCid").val();
        var date = $("#delDate").val();
        $.post("handleSD",
                {
                    action: "Delete",
                    cid: cid,
                    date: date
                },
                function (data, status) {
                    //alert(cid +date+ data)
                    if (data === "Success") {
                        calendar.refetchEvents();
                        $("#deleteDateModal").modal('toggle');
                    }
                });
    }

    function updateCal() {
        var calendarEl = document.getElementById('calendar');
        calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['interaction', 'dayGrid', 'timeGrid', 'list'],
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
            },
            defaultDate: moment().format("YYYY-MM-DD"),
            editable: false,
            navLinks: true, // can click day/week names to navigate views
            eventLimit: true, // allow "more" link when too many events
            events: {
                url: 'handleTimeTable?cid=' + $("#classSelect").val(),
                failure: function () {
                }
            },
            loading: function (bool) {
                document.getElementById('loading').style.display =
                        bool ? 'block' : 'none';
            }
        });
        calendarEl.innerHTML = "";
        calendar.render();
    }

    $(document).ready(function () {
        $.get("handleClass?action=printAllClass", function (data, status) {
            var strArray = data.split(",");
            strArray.forEach(addSelect);
            updateCal();
        });

        $("#classSelect").change(function () {
            updateCal();
        });
    });

</script>