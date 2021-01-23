<%-- 
    Document   : loginSuccessAdmin
    Created on : Nov 30, 2020, 5:30:17 PM
    Author     : mahit
--%>

<%@page import="com.mycompany.pojo.User"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>BOOTSTRAP</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <style>
        body {
            position: relative;
            background-color: #d7d7d7;
        }
        #carousel-example {
            padding-top: 70px;
            padding-left: 10px;
            padding-right: 10px;
        }
        .navbar-dark .navbar-nav .nav-link {
            color: #fcfcfc;
        }
    </style>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">
    <nav
        class="navbar navbar-expand-md navbar-dark fixed-top"
        style="background-color: #BC544B"
        type="button"
        data-toggle="collapse"
        data-target="#collapsibleNavbar"
        >

              <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <a class="nav-link" href="redirect.jsp" > <img src="logo.png" alt="/logo" width="45px" /></a>
            <i><bold><strong>Spicy Food</strong></bold></i>
            <ul class="navbar-nav mr-auto">

               <li class="nav-item">
                    <a class="nav-link" class="btn btn-primary"  href="updatedeliv.htm"><strong>Update Profile</strong></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" class="btn btn-primary" role="button" id="navbardrop" data-toggle="dropdown">
                        <strong>View Orders</strong>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="ordersassigneddev.htm">Assigned</a>
                        <a class="dropdown-item" href="ordersdelivered.htm">Delivered</a>
                    </div>
                </li>

            </ul>
            <ul class="navbar-nav ml-auto">
 
                <li class="nav-item">
                    <a class="nav-link" class="btn btn-primary"  href="logout.htm"><strong>Logout</strong></a>
                </li>
            </ul>
        </div>
    </nav>

    <br><br><br><br> 
   
    <div>
        <h1>Update profile below!!</h1>
        <c:set var="user4" value="${requestScope.user3}"></c:set>
        <form:form modelAttribute="user3">
            <div class="form-group">
                <label class="control-label">Edit Phone Number:</label>
                <div>
                    <form:input type="text" path="phoneNum" value="${user4.phoneNum}" class="form-control input-lg" name="phoneNum"/>
                    <form:errors path="phoneNum"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">Edit Address:</label>
                <div>
                    <form:textarea path = "address" value="${user4.address}" rows = "5" cols = "30" class="form-control input-lg" name="address"/>
                    <form:errors path="address"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">Edit Password:</label>
                <div>
                    <form:input path="password" type="password" value="${user4.password}" class="form-control input-lg" name="password"/>
                    <form:errors path="password"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <button type="submit" id="register" class="btn btn-success">
                        Update profile!!
                    </button>
                </div>
            </div>
            <div class="form-group">
                <form:errors path="*"/>
            </div>
        </form:form> 
    </div>

    <br><br> <br><br> <br><br> <br><br>
    <br><br> <br><br> <br><br> <br><br>
    <!-- Footer -->
    <footer class="page-footer font-small special-color-dark pt-4" style="background-color: #BC544B;">

        <!-- Footer Elements -->
        <div class="container">

            <!-- Social buttons -->
            <ul class="list-unstyled list-inline text-center">
                <li class="list-inline-item">
                    <a class="btn-floating btn-fb mx-1">
                        <i class="fab fa-facebook-f"> </i>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a class="btn-floating btn-tw mx-1">
                        <i class="fab fa-twitter"> </i>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a class="btn-floating btn-gplus mx-1">
                        <i class="fab fa-google-plus-g"> </i>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a class="btn-floating btn-li mx-1">
                        <i class="fab fa-linkedin-in"> </i>
                    </a>
                </li>
                <li class="list-inline-item">
                    <a class="btn-floating btn-dribbble mx-1">
                        <i class="fab fa-dribbble"> </i>
                    </a>
                </li>
            </ul>
            <!-- Social buttons 782A61 -->

        </div>
        <!-- Footer Elements -->

        <!-- Copyright -->
        <div class="footer-copyright text-center py-3" style="background-color: #BC544B;">&copy; 2020 Copyright:
            <i> LMNTrix</i>
        </div>
        <!-- Copyright -->

    </footer>
    <!-- Footer -->


</body>
</html>
