<%-- 
    Document   : signup
    Created on : Nov 22, 2020, 12:40:42 AM
    Author     : mahit
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />
        <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
 
    <style>
        body {

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
                    <a class="nav-link" class="btn btn-primary"  href="signupDelivery.htm"><strong>Wanna Delivery food?</strong></a>
                </li>

                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" class="btn btn-primary"  href="signup.htm"><strong>Sign up</strong></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" class="btn btn-primary"  href="login.htm"><strong>Login</strong></a>
                    </li>
                </ul>
            </div>
        </nav>
        <br><br><br><br>  
        
        <div style="width:1500px; margin:0 auto;">
            <h3>New user? Register below!</h3>
            <form:form modelAttribute="user">
               
                <div class="form-group w-25" >
                    <label class="control-label">First Name</label>
                    <div>
                        <form:input type="text" class="form-control" name="firstName" path="firstName"/>
                        <form:errors path="firstName"/>
                    </div>
                </div>
                <div class="form-group w-25">
                    <label class="control-label">Last Name</label>
                    <div>
                        <form:input path="lastName" type="text" class="form-control" name="lastName" id="lastName"/>
                        <form:errors path="lastName"/>
                    </div>
                </div>
                <div class="form-group w-25">
                    <label class="control-label">Phone Number</label>
                    <div>
                        <form:input path="phoneNum" type="text" class="form-control input-lg" name="phoneNum" />
                        <form:errors path="phoneNum"/>
                    </div>
                </div>
                    
                 <div class="form-group w-25">
                    <label class="control-label">Address</label>
                    <div>
                        <form:textarea path = "address" rows = "5" cols = "30" class="form-control input-lg" name="address" />
                        <form:errors path="address"/>
                    </div>
                </div>
                <div class="form-group w-25">
                    <label class="control-label">E-Mail Address</label>
                    <div>
                        <form:input path="emailId" type="text" class="form-control input-lg" name="emailId"/>
                        <form:errors path="emailId"/>
                    </div>
                </div>
                <div class="form-group w-25">
                    <label class="control-label">Password</label>
                    <div>
                        <form:input path="password" type="password" class="form-control input-lg" name="password" />
                        <form:errors path="password"/>
                    </div>
                </div>
                
               
                <div class="form-group">
                    <div>
                        <button type="submit" id="register" class="btn btn-success">
                            Register
                        </button>
                    </div>
                </div>
                     <div class="form-group">
                 <form:errors path="*"/>
                   </div>
            </form:form> 
            Already have an account? <a href="login.htm" >login </a>
        </div>
        
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
    </body>
</html>
