<%-- 
    Document   : adminOrders
    Created on : Nov 30, 2020, 6:33:55 PM
    Author     : mahit
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.dao.OrderDao"%>
<%@page import="com.mycompany.pojo.OrderItem"%>
<%@page import="com.mycompany.dao.OrderItemDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.pojo.Order"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <a class="nav-link" class="btn btn-primary"  href="adminOrders.htm"><strong>Orders</strong></a>
                </li>
                 <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" class="btn btn-primary" role="button" id="navbardrop" data-toggle="dropdown">
                        Menu
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="viewAdminMenu.htm">View/Edit Menu</a>
                        <a class="dropdown-item" href="addMenuItem.htm">Add Menu Item</a>
                        <a class="dropdown-item" href="deleteMenuItem.htm">Delete Menu Item</a>
                    </div>
                </li>
               <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" class="btn btn-primary" role="button" id="navbardrop" data-toggle="dropdown">
                        Delivery Men
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="approveDelivRequests.htm">Approve Signup Requests</a>
                        <a class="dropdown-item" href="deleteAccounts.htm">Delete Account</a>
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
        <h1>Sign Up requests from Delivery Men!!</h1>
        <%
            String error = (String) request.getAttribute("error");
            int err = 1;
            if (error == null) {
                err = 0;
            }
            pageContext.setAttribute("err", err);
            pageContext.setAttribute("error", error);
        %>
        <c:set value="${requestScope.pendingRequests}" var="pendingRequests"></c:set> 

            <table class="table table-striped table-dark">
                <tr><th>User Id</th><th>FirstName</th><th>LastName</th><th>Phone Number</th><th>Email ID</th><th>Address</th>
                    <th></th></tr>
                    <c:forEach items="${pendingRequests}" var="item">

                <tr class="bg-info">
                    <td> <c:out value="${item.userId}"></c:out></td>
                    <td><c:out value="${item.firstName}"></c:out></td>
                    <td><c:out value="${item.lastName}"></c:out></td>
                    <td><c:out value="${item.phoneNum}"></c:out></td>
                    <td><c:out value="${item.emailId}"></c:out></td>
                    <td><c:out value="${item.address}"></c:out></td>
                    <td>
                        <form method="post" action="approveDelivRequests.htm" >
                            <input type="hidden" value="accept" name="option"/>
                            <input type="hidden" value="${item.emailId}" name="emailId"/>
                            <input type="hidden" value="${item.userId}" name="userId"/>
                            <input type="submit" class="btn-info" value="Accept"/>
                        </form>
                        <form method="post" action="approveDelivRequests.htm">
                            <input type="hidden" value="reject" name="option"/>
                            <input type="hidden" value="${item.emailId}" name="emailId"/>
                            <input type="hidden" value="${item.userId}" name="userId"/>
                            <input type="submit" value="Reject"/>
                        </form>
                    </td>
                </tr>
            </c:forEach> 
        </table>

        <hr>
        <h1>List of Delivery Men!!</h1>
        <hr>

        <c:set value="${requestScope.acceptedRequests}" var="acceptedRequests"></c:set> 
        <table class="table table-striped table-dark">
                <tr class="bg-info">
                    <th>User Id</th><th>FirstName</th><th>LastName</th><th>Phone Number</th><th>Email ID</th><th>Address</th>
                </tr>
            <c:forEach items="${acceptedRequests}" var="item1">
                <tr>
                    <td><c:out value="${item1.userId}"></c:out></td>
                    <td><c:out value="${item1.firstName}"></c:out></td>
                    <td><c:out value="${item1.lastName}"></c:out></td>
                    <td><c:out value="${item1.phoneNum}"></c:out></td>
                    <td><c:out value="${item1.emailId}"></c:out></td>
                    <td><c:out value="${item1.address}"></c:out></td>
                </tr>
            </c:forEach> 
        </table>



    </div>

    <br><br><br><br><br><br><br><br>
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
