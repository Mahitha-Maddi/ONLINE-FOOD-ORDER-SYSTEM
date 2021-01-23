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
        <%
            String error = (String) request.getAttribute("error");
            int err = 1;
            if (error == null) {
                err = 0;
            }
            pageContext.setAttribute("err", err);
            pageContext.setAttribute("error", error);
            List<Order> placedOrders = (List<Order>) request.getAttribute("placedOrders");
        %>
        <h1>Orders places by Customers!!</h1>
        <c:set value="${requestScope.placedOrders}" var="placedOrders"></c:set> 

            <table class="table table-striped table-dark">
                <tr ><th>Order Id</th><th>Delivery Address</th><th>Items</th><th>Total Price</th><th></th></tr>
                    <c:forEach items="${placedOrders}" var="item">

                <tr class="bg-info">
                    <td> <c:out value="${item.orderId}"></c:out></td>
                    <td><c:out value="${item.orderAddress}"></c:out></td>
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
                        <c:out value="${item.orderTotalPrice}"></c:out>
                        </td>
                        <td>
                            <form method="post" action="adminOrders.htm" >
                                <input type="hidden" value="accept" name="option"/>
                                <input type="hidden" value="${item.orderId}" name="orderId"/>
                            <input type="submit" value="Accept"/>
                        </form>
                        <form method="post" action="adminOrders.htm">
                            <input type="hidden" value="reject" name="option"/>
                            <input type="hidden" value="${item.orderId}" name="orderId"/>
                            <input type="submit" value="Reject"/>
                        </form>
                    </td>
                </tr>
            </c:forEach> 
        </table>

        <hr>
        <h1>Assign Delivery Men</h1>
        <hr>

        <c:set value="${requestScope.acceptedOrders}" var="acceptedOrders"></c:set> 
            <table class="table table-striped table-dark">
                <tr class="bg-info"><th>Order Id</th><th>Delivery Address</th><th>Items</th><th>Total Price</th><th></th></tr>
                    <c:forEach items="${acceptedOrders}" var="item1">

                <tr>
                    <td> <c:out value="${item1.orderId}"></c:out></td>
                    <td><c:out value="${item1.orderAddress}"></c:out></td>
                        <td>
                        <%
                            Order order1 = (Order) pageContext.getAttribute("item1");
                            OrderItemDao orderItemDao1 = new OrderItemDao();
                            ArrayList<OrderItem> orderItems1 = (ArrayList<OrderItem>) orderItemDao1.getOrderItems(order1.getOrderId());
                            for (OrderItem orderItem : orderItems1) {
                                out.println(orderItem.getItemName());
                            }
                        %>
                    </td>
                    <td>
                        <c:out value="${item1.orderTotalPrice}"></c:out>
                        </td>
                        <td>
                            <form method="post" action="adminOrders.htm" >
                                <input type="hidden" value="assign" name="option"/>
                                <input type="hidden" value="${item1.orderId}" name="orderId"/>
                            <select name="deliveryManName">
                                <c:forEach items="${requestScope.deliveryMen}" var="item5">
                                    <option value="${item5.userId}">${item5.firstName} ${item5.lastName}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Assign"/>
                        </form>

                    </td>
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
