<%-- 
    Document   : loginSuccessAdmin
    Created on : Nov 30, 2020, 5:30:17 PM
    Author     : mahit
--%>

<%@page import="com.mycompany.dao.UserDao"%>
<%@page import="com.mycompany.pojo.OrderItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.dao.OrderItemDao"%>
<%@page import="com.mycompany.pojo.Order"%>
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

    <br><br> <br><br> 
    <h1>Orders Assigned!</h1>

    <div>
        <c:set var="assignedOrders" value="${requestScope.assignedOrders}"></c:set>
        
        <table class="table table-striped table-dark">
            <tr class="bg-info"><th>OrderID</th><th>Order Items</th><th>Customer Name</th><th>Delivery Address</th>
                <th>Order Total Price</th><th></th></tr>
         <c:forEach items="${assignedOrders}" var="item">

                <tr>
                    <td> <c:out value="${item.orderId}"></c:out></td>
                    <td>
                        <%
                            Order order = (Order) pageContext.getAttribute("item");

                            OrderItemDao orderItemDao = new OrderItemDao();
                            ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) orderItemDao.getOrderItems(order.getOrderId());
                            for (OrderItem orderItem : orderItems) {
                                out.println(orderItem.getItemName());
                            }
                        %>
                    </td>
                    <td>
                        <%
                            Order order1 = (Order) pageContext.getAttribute("item");
                            int customerid=(int)order1.getCustomerId();
                            UserDao userDao = new UserDao();
                            User customer = (User) userDao.getDeliveryMan(customerid);
                            out.println(customer.getFirstName()+" "+customer.getLastName());
                        %>
                    </td>
                    
                    <td><c:out value="${item.orderAddress}"></c:out></td>
                    <td>
                        <c:out value="${item.orderTotalPrice}"></c:out>
                    </td>
                    <td>
                       <form method="post" action="ordersassigneddev.htm">
                            <input type="hidden" value="${item.orderId}" name="orderId"/>
                            <input type="submit" value="Delivered"/>
                        </form>
                    </td>
                </tr>
            </c:forEach> 
        </table>
    </div>

    <br><br> <br><br> <br><br> <br><br>
    <br><br> <br><br> <br><br> <br><br><br> 
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
