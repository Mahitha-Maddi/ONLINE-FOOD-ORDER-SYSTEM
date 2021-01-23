<%-- 
    Document   : loginSuccess
    Created on : Nov 23, 2020, 1:06:38 AM
    Author     : mahit
--%>
<%@page import="com.mycompany.pojo.MenuItem"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>BOOTSTRAP</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <style>
        * {box-sizing: border-box}
        body {font-family: "Lato", sans-serif;}

        /* Style the tab */
        .tab {
            float: left;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
            width: 30%;
            height: 1000px;

        }

        /* Style the buttons inside the tab */
        .tab button {
            display: block;
            background-color: inherit;
            color: black;
            padding: 22px 16px;
            width: 100%;
            border: none;
            outline: none;
            text-align: left;
            cursor: pointer;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current "tab button" class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            float: left;
            padding: 0px 12px;
            border: 1px solid #ccc;
            width: 70%;
            border-left: none;
            height: 1000px;
            background-color: white;

        }
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

    <br><br><br>
    <div>
        <a class="nav-link" class="btn btn-primary"  href="searchItems.htm"><strong>Go back to search page</strong></a>
        <div>
            
            
            <c:set value="${requestScope.menuItemList}" var="menuItemList"></c:set>
            <c:if test="${empty menuItemList}">
                No Item Found!!
            </c:if>
            <c:if test="${not empty menuItemList}">
                <c:forEach items="${menuItemList}" var="item">
                <c:set var="id" value="${fn:replace(item.itemName, ' ', '')}"/>
                <c:set var="id1" value="#${fn:replace(item.itemName, ' ', '')}"/>
                <div class="card">
                    <div class="card-body">
                    <h4 class="card-title" > 
                        <button role="button" data-toggle="modal" data-target="${id1}"><c:out value="${item.itemName}"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            $<c:out value="${item.itemPrice}"/> <br>
                        </button>
                    </h4>
                    <p class="card-text">
                    <form method="post" id="buildyourform">
                        <input type="hidden" name="itemName" value="${item.itemName}"/>
                        <input type="hidden" name="description" value="${item.description}"/>
                        Qty:<input type="number" name="itemQty" id="Qty" min="1" max="10" required="true"/><br><br>
                        <input type="submit" class="submit" id="submit" value="Add to Cart"> 
                    </form>
                    </p>
                    <c:set var="category" value="${item.category}"> </c:set>

                            <c:choose>
                                <c:when test="${fn:contains(category, 'non')}">
                                    <img src="nonveg.JPG" style="width:50px;height:50px" alt="Non Veg"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="veg.JPG" style="width:50px;height:50px" alt="Veg"/>
                                </c:otherwise>
                            </c:choose>
                    <!--modal-->
                    <div class="modal fade" id="${id}" tabindex="-1"  aria-labelledby="modalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                        <div class="modal-dialog"  data-backdrop="static" data-keyboard="false" >
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="modalLabel"><br></h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <c:forEach items="${item.itemImages}" var="itemImage">
                                        <img 
                                            class="card-img-top" 
                                            style="width:100%;height:260px" 
                                            alt="Card image" 
                                            src="<c:url value="/"/>${itemImage.imageName}"
                                            />
                                    </c:forEach>
                                    <c:out value="${item.description}"/> 
                                </div>
                                <div class="modal-footer"></div>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
                                </c:forEach>
            </c:if>


        </div>


    </div>


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

        /////////////////////////////////////////////////////AJAX CALL
        //$("#submit").click(function (event)
        $(document).on('click', '.submit', function (event)
        {
            event.preventDefault();
            //alert("hey");
            var par = $(event.target).parent();
            // var children=[];
            //   children=par.children();
            var children = par.find("input");
            var Qty = children.eq(2).val();

            var itemName = children.eq(0).val();
            var description = children.eq(1).val();

            /**var developerData = {};
             developerData["itemName"] = itemName;
             developerData["description"] = description;
             developerData["Qty"] = Qty;
             // PREPARE FORM DATA
             var formData = {
             itemName: itemName,
             Qty: Qty,
             description: description
             };
             var dataJson = JSON.stringify(formData);
             **/
            var xhttp;
            if (window.XMLHttpRequest) {
                // code for modern browsers
                xhttp = new XMLHttpRequest();
            } else {
                // code for IE6, IE5
                xhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            xhttp.onreadystatechange = function () {

                //alert(this.readyState);
                if (this.readyState === 4 && this.status === 202) {
                    alert("Successfully added to cart!!");
                    children.eq(2).val("");
                }
            };

            xhttp.open("POST", "addCart.htm", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("itemName=" + itemName + "&Qty=" + Qty + "&description=" + description);
        });
    </script>


</body>
</html>
