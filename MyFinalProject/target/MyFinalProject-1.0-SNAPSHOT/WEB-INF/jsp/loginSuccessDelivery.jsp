<%-- 
    Document   : loginSuccessAdmin
    Created on : Nov 30, 2020, 5:30:17 PM
    Author     : mahit
--%>

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

    
   <div
        id="carousel-example"
        class="carousel slide carousel-fade"
        data-ride="carousel"
        >

        <ol class="carousel-indicators">
            <li
                data-target="#carousel-example"
                data-slide-to="0"
                class="active"
                ></li>
            <li data-target="#carousel-example" data-slide-to="1"></li>
            <li data-target="#carousel-example" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner" role="listbox">

            <div class="carousel-item active">
                <img
                    class="d-block w-100"
                    src="carousel1.jpg"
                    alt="First slide"
                    />
            </div>

            <div class="carousel-item">
                <img
                    class="d-block w-100"
                    src="carousel2.jpg"
                    alt="Second slide"
                    />
            </div>
            <!--/Second slide-->
            <!--Third slide-->
            <div class="carousel-item">
                <img
                    class="d-block w-100"
                    src="carousel3.jpg"
                    alt="Third slide"
                    />
            </div>
            <!--/Third slide-->
        </div>
        <!--/.Slides-->
        <!--Controls-->
        <a
            class="carousel-control-prev"
            href="#carousel-example"
            role="button"
            data-slide="prev"
            >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a
            class="carousel-control-next"
            href="#carousel-example"
            role="button"
            data-slide="next"
            >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>

    </div>

    <div class="jumbotron">
        <h1><span class="badge badge-warning">Welcome Food lovers.!!</span></h1>
        <strong>Yummy Food in your Tummy!!</strong><br>
        Get it delivered right to your door. Or, try Pickup on your way home. <br>It’s mealtime on your time.

    </div>

    <div
        id="section1"
        class="container-fluid"
        style="padding-top:70px;padding-bottom:70px"
        >
        <div class="row" style="padding-bottom: 20px;">

            <!--Cards-->
            <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                <div class="card" style="height:450px">
                    <img
                        class="card-img-top"
                        src="burgers.jpg"
                        alt="Card image"
                        style="width:100%;height:260px"
                        />
                    <div class="card-body">
                        <h4 class="card-title">Burgers</h4>
                        <p class="card-text" style="height:40px">
                            Our juicy burgers are hand smashed and served with piping hot!

                        </p>
                        <a  class="btn btn-info" role="button" data-toggle="modal" data-target="#f1" >View Details</a>
                        <!--modal-->
                        <div class="modal fade" id="f1" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title" id="modalLabel">Burgers</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Our juicy burgers are hand smashed and served with piping hot, 
                                        perfectly seasoned fries.classic cheese burger, easy chicken burgers, and 
                                        more homemade hamburgers. 
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                <div class="card" style="height:450px">
                    <img
                        class="card-img-top"
                        src="snacks.jpg"
                        alt="Card image"
                        style="width:100% ;height:260px"
                        />
                    <div class="card-body">
                        <h4 class="card-title">Snacks</h4>
                        <p class="card-text" style="height:40px">
                            Snacking does n't automatically mean weight gain.
                        </p>
                        <a  class="btn btn-info" role="button" data-toggle="modal" data-target="#f2">View Details</a>

                        <!--modal-->
                        <div class="modal fade" id="f2" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title" id="modalLabel">Snacks</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Snacking does n't automatically mean weight gain. 
                                        For the best healthy snacks to buy for weight loss, look no further.
                                        Healthy snacks gluten free, low calorie, high protein, low fat and low sugar!!
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                <div class="card" style="height:450px">
                    <img
                        class="card-img-top"
                        src="pizzas.jpg"
                        alt="Card image"
                        style="width:100%;height:260px"
                        />
                    <div class="card-body">
                        <h4 class="card-title">Pizzas</h4>
                        <p class="card-text" style="height:40px">
                            We make our pizzas with the finest ingredients!!
                        </p>
                        <a class="btn btn-info" role="button" data-toggle="modal" data-target="#f3">View Details</a>


                        <!--modal-->
                        <div class="modal fade" id="f3" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title" id="modalLabel">Pizzas</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        We make our pizzas with the finest ingredients 
                                        using fresh dough made daily on the premises, 
                                        a special blend of Cheese and topped with the freshest toppings of your choice. 
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-lg-3 col-md-4 col-sm-6 col-12">
                <div class="card" style="height:450px">
                    <img
                        class="card-img-top"
                        src="desserts.jpg"
                        alt="Card image"
                        style="width:100% ;height:260px"
                        />
                    <div class="card-body">
                        <h4 class="card-title">Desserts</h4>
                        <p class="card-text" style="height:40px">
                            Taste these festive brownies for an easy holiday treat.
                        </p>
                        <a class="btn btn-info" data-target="#f4" data-toggle="modal">View Details</a>

                        <div class="modal fade" id="f4" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title" id="modalLabel">Desserts</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Taste these festive brownies for an 
                                        easy holiday treat. Place the order! Plant Protein, Dietary Fiber, Antioxidant Vitamins!!
                                        A collection of sweets and pastries to satisfy your cravings. 
                                        Cakes | Pies | Cookies | Ice Cream | Brownies!!
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </div>
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



