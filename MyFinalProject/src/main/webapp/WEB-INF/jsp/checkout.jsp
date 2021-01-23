<%-- 
    Document   : checkout
    Created on : Nov 24, 2020, 4:33:10 PM
    Author     : mahit
--%>
<%-- 
    Document   : viewCart
    Created on : Nov 23, 2020, 10:23:20 PM
    Author     : mahit
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        .address {
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
<h1>Checkout page!</h1>

                
Total Amount: &nbsp;
<%
    List<OrderItem> orderItemsList = (List<OrderItem>) session.getAttribute("items");
    double totalAmount=0;
    if(orderItemsList!=null){
     for (OrderItem item : orderItemsList) {
     totalAmount=totalAmount+item.getTotalPrice();
    }}
    out.println(totalAmount);

%>
<hr>
<form:form modelAttribute="debitCard" method="POST" action="checkout.htm">
    <input type="radio" name="radioBtn" value="default" onclick="myFunction()" checked="true">Default Address
    <br>
    <textarea name="address" class="address" style="background-color: white;color:black;" readonly>${requestScope.customerAddress}</textarea><br>
    <input type="radio" name="radioBtn" value="other" onclick="myFunction()">Other Address
    <br>
    <textarea name="address1" class="address" style="background-color: white;color:black;" id="address1"></textarea>
    <hr>
    Payment Mode:<br>
    <input type="radio" name="paymentMode" value="debitCard" required/>Debit Card<br>
    <input type="radio" name="paymentMode" value="creditCard" required/>Credit Card<br>
    Card Details: <br>
    Name on Card: &nbsp;<form:input path="cardName" type="text" name="cardName" required="true"/> <form:errors path="cardName"/>
    Card Number: &nbsp;<form:input path="cardNumber" type="text" name="cardNumber" required="true"/> <form:errors path="cardNumber"/><br>
    Expiration: &nbsp;<form:input path="cardExpiration" type="text" name="cardExpiration" required="true"/> <form:errors path="cardExpiration"/><br>
    CVV: &nbsp;<form:input path="cardCVV" type="text" name="cardCVV" required="true"/> <form:errors path="cardCVV"/><br>
    <form:errors path="*"/>
    <hr>
    <input class="btn btn-info" type="submit" value="Place Order">
</form:form>
  

 
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

<script>
function myFunction() {
    var radios = document.getElementsByName('radioBtn');
    for (var i = 0, length = radios.length; i < length; i++) 
    {
       if (radios[i].checked) 
       {
           var value=radios[i].value.toString();
           var str="default";
           var x =document.getElementById("address1");
         if(value===(str)){
          x.required = false;
           
          }
         else{
          x.required = true;
         
          }
      }
     }
}
</script>

</body>
</html>
