<%-- 
    Document   : adminOrders
    Created on : Nov 30, 2020, 6:33:55 PM
    Author     : mahit
--%>
<%@page import="com.mycompany.pojo.OrderItem"%>
<%@page import="com.mycompany.dao.OrderItemDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.pojo.Order"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <br><br><br>
    <div>
        <hr>
        <h1>Add Menu Item</h1>
        <hr>


        <form:form modelAttribute="menuItem" enctype="multipart/form-data">
            <input type="hidden" value="add" name="option"/>
            <div class="form-group">
                <label class="control-label">Cuisine</label>
                <div>
                    <form:select path="cuisine">
                        <form:option value="soup"> --Soup--</form:option>
                        <form:option value="appetizer"> --Appetizer--</form:option>
                        <form:option value="pizza"> --Pizza--</form:option>
                        <form:option value="burger"> --Burger--</form:option>
                        <form:option value="snack"> --Snack--</form:option>
                        <form:option value="dessert"> --Dessert--</form:option>
                        <form:option value="drink"> --Drink--</form:option>
                    </form:select>
                    <form:errors path="cuisine"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">Item Name</label>
                <div>
                    <form:input path="itemName" type="text" class="form-control" name="itemName"/>
                    <form:errors path="itemName"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">Item Price</label>
                <div>
                    <form:input path="itemPrice" type="number" value="1" step=".01" min="1" class="form-control input-lg" name="itemPrice" />
                    <form:errors path="itemPrice"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">Description</label>
                <div>
                    <form:textarea path = "description" rows = "5" cols = "30" class="form-control input-lg" name="description" />
                    <form:errors path="description"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">Category</label>
                <div>
                    <form:select path="category">
                        <form:option value="veg"> --Veg--</form:option>
                        <form:option value="nonVeg"> --Non Veg--</form:option>
                    </form:select>
                    <form:errors path="category"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">Upload Image</label>
                <div>
                    <input type="file"  name="file" multiple="multiple" required="true" />
                </div>
            </div>
            <div class="form-group">
                <div>
                    <button type="submit" id="register" class="btn btn-success">
                        Add Item
                    </button>
                </div>
            </div>
            <div class="form-group">
                <form:errors path="*"/>
            </div>
        </form:form> 



    </div>


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
