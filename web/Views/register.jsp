<%-- 
    Document   : Jsp1
    Created on : May 27, 2022, 1:11:02 PM
    Author     : Misaki
--%>

<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>misaki.</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,700" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <div id="colorlib-page">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="js-fullheight text-center">
                <h1 id="colorlib-logo"><a href="index.jsp">misaki<span>.</span></a></h1>
                
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                           <li><a href="index.jsp">Home</a></li>
                            <c:forEach items="${requestScope.DAO.cateHm}" var="cate">
                            <li><a href='NewsController?type=0&id=${cate.value.id}'>${cate.value.name}</a></li>
                            </c:forEach>
                    </ul>
                    <div class="btn-group">
                        <button type="img" class="btn btn-dark"> <img src="images/avatar.png" class="rounded-circle"
                                                                      height="22" alt="Avatar" loading="lazy" /></button>
                        <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>

                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/LoginController">Login</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/RegisterController">Register</a>
                            <!--                        <a class="dropdown-item" href="#">Something else here</a>
                                                    <div class="dropdown-divider"></div>
                                                    <a class="dropdown-item" href="#">Separated link</a>-->
                        </div>
                    </div>
                </nav>
                
                <div class="colorlib-footer">
                    <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This website is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://www.facebook.com/khaitrinhdinh1209/" target="_blank">TrKhaii</a>
                    <ul>
                        <li><a href="https://www.facebook.com/khaitrinhdinh1209/"><i class="icon-facebook"></i></a></li>
                        <li><a href="https://twitter.com/?lang=en"><i class="icon-twitter"></i></a></li>
                        <li><a href="https://www.instagram.com/"><i class="icon-instagram"></i></a></li>
                        <li><a href="https://www.linkedin.com/posts/scottdresden_ethereums-merge-is-coming-and-the-stakes-activity-6938512169353314305-fvwI/"><i class="icon-linkedin"></i></a></li>
                    </ul>
                </div>
            </aside>
            <div id="colorlib-main">    
                <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
                    <div class="card-body p-4 p-lg-5 text-black">
                        <form action="<%=request.getContextPath()%>/RegisterController" method="post">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Register</span>
                            </div>

                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign up new account</h5>
                            <div class="alert alert-success center mb-4" role="alert">
                                <p>${NOTIFICATION}</p>
                            </div>

                            <div class="form-outline mb-4">
                                Full name: <input type="text" class="form-control form-control-lg" id="uname" placeholder="Full Name" name="fullname" required>
                            </div>

                            <div class="form-outline mb-4">
                                Username: <input type="text" class="form-control form-control-lg" id="username" placeholder="User Name" name="username" required>
                            </div>

                            <div class="form-outline mb-4">
                                Password: <input type="password" class="form-control form-control-lg" id="password" placeholder="Password" name="password" required>
                            </div>

                            <div class="form-outline mb-4">
                                Email: <input type="text" class="form-control form-control-lg" id="email" placeholder="Email" name="email" required>
                            </div>

                            <div class="form-outline mb-4">
                                Gender: <input type='radio' name='gender' value='male'> Male
                                <input type='radio' name='gender' value='female'> Female<br/>
                            </div>
                            
                            <div class="form-outline mb-4">
                                Date of birth: <input type='date' name='dob'>
                            </div>
                            
                            <div class="form-outline mb-4">
                                Address: <input type="text" class="form-control form-control-lg" id="address" placeholder="Address" name="address" required>
                            </div>
                            
                            <div class="form-outline mb-4">
                                Avatar: <input type="text" class="form-control form-control-lg" id="avatar" placeholder="Link to your avatar" name="avatar" required>
                            </div>

                            <div class="form-outline mb-4">
                                Security Question: <select class="form-control form-control-lg"  name="quesId" >
                                    <c:forEach items = "${requestScope.DAO.questHm}" var="i">
                                        <option value="${i.value.id}">${i.value.question}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-outline mb-4">
                                Answer: <input type="text" class="form-control form-control-lg" id="answer" placeholder="Answer" name="answer" required>
                            </div>
                            




                            <div class="pt-1 mb-4">
                                <button class="btn btn-dark btn-lg btn-block" type="submit">Sign up</button>
                            </div>

                            <div class="text-center">
                                <p>or sign up with:</p>
                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="icon-facebook"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="icon-twitter"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="icon-instagram"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="icon-linkedin"></i>
                                </button>
                            </div>


                            <a href="#!" class="small text-muted">Terms of use.</a>
                            <a href="#!" class="small text-muted">Privacy policy</a>
                        </form>

                    </div>
                </div>


                <footer class="ftco-footer ftco-bg-dark ftco-section">
                    <div class="container px-md-5">
                        <div class="row mb-5">
                            <div class="col-md">
                                <div class="ftco-footer-widget mb-4">
                                    <h2 class="ftco-heading-2">Have a Questions?</h2>
                                    <div class="block-23 mb-3">
                                        <ul>
                                            <li><span class="icon icon-map-marker"></span><span class="text">FPT University, Thach Hoa, Thach That, Hanoi</span></li>
                                            <li><a href="#"><span class="icon icon-phone"></span><span class="text">+84 911 1209 02</span></a></li>
                                            <li><a href="#"><span class="icon icon-envelope"></span><span class="text">khaitrinhdinh12@gmail.com</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">

                                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This website is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://www.facebook.com/khaitrinhdinh1209/" target="_blank">TrKhaii</a>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div><!-- END COLORLIB-MAIN -->
        </div><!-- END COLORLIB-PAGE -->


        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/jquery.timepicker.min.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
