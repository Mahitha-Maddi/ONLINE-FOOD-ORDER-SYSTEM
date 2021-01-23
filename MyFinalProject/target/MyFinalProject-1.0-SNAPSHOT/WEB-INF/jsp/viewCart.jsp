<%-- 
    Document   : viewCart
    Created on : Nov 23, 2020, 10:23:20 PM
    Author     : mahit
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.mycompany.pojo.OrderItem"%>
<%@page import="java.util.List"%>
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
                <a class="nav-link" class="btn btn-primary"  href="menu.htm"><strong>Menu</strong></a>
            </li>
            <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" class="btn btn-primary" role="button" id="navbardrop" data-toggle="dropdown">
                        Previous Orders
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="provideFeedback.htm">Provide Feedback</a>
                        <a class="dropdown-item" href="rejectedOrders.htm">View Cancelled/Rejected Orders</a>
                    </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" class="btn btn-primary"  href="trackOrders.htm"><strong>Track Orders</strong></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" class="btn btn-secondary"  href="searchItems.htm"><strong>Search Items</strong></a>
            </li>
            


        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" class="btn btn-primary"  href="viewCart.htm"><strong>View Cart</strong></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" class="btn btn-primary"  href="logout.htm"><strong>Logout</strong></a>
            </li>
        </ul>
    </div>
</nav>

<br><br> <br><br> 
<%
    List<OrderItem> orderItemsList = (List<OrderItem>) session.getAttribute("items");
    List<OrderItem> orderItemsList1 = (List<OrderItem>) request.getAttribute("items1");

%>

<div>
        <a role="button" class="btn btn-info" href="checkout.htm">Checkout</a>
    </div>

 <% String error=(String)request.getAttribute("error");
        if(error!=null){
        out.println(error);
            }
        %>

<c:forEach var="item" items="${sessionScope.items}">
  
    
 
       
         <c:set var="id" value="${item.itemName}"/>

            <c:set var="id1" value="#${item.itemName}"/>
            <div class="card"  >
               
                    <h4 class="card-title" > 
                         <button role="button" data-toggle="modal" data-target="${id1}"><c:out value="${item.itemName}"/>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 </button>
   
                        
                    </h4>
                    <p class="card-text">
                         <form method="post" action="viewCart.htm">
                            <input type="hidden" name="itemName" value="${item.itemName}"/>
                            <input type="hidden" name="optionValue" value="editQty"/>
                   Edit Qty:<input type="number" name="itemQty" value="${item.quantity}" min="1" max="10"/><br><br>
                           Total Price: <c:out value="${item.totalPrice}" ></c:out>
                            <input type="submit" value="Edit Qty"> 
                        </form>
                        <form method="post" action="viewCart.htm">
                            <input type="hidden" name="itemName" value="${item.itemName}"/>
                            <input type="hidden" name="optionValue" value="remove"/>
                            <input type="submit" value="Remove from Cart"> 
                        </form>
                    </p>

                    <!--modal-->
                    <div class="modal fade" id="${id}" tabindex="-1"  aria-labelledby="modalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                        <div class="modal-dialog"  data-backdrop="static" data-keyboard="false" >
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="modalLabel">
                                        <br>
                                    </h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                      
                                <div class="modal-body">
                                 <c:out value="${item.description}"/> 
                                    
                                </div>
                                <div class="modal-footer">
                                 
                                </div>
                                    
                            </div>
                        </div>
                    </div>

                
            </div>


</c:forEach>





<br><br> <br><br> <br><br> <br><br> <br><br>
<br><br> <br><br> <br><br> <br><br> <br><br>
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
