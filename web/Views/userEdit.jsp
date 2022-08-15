<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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

        <c:set var="cMap" value="${requestScope.DAO.cateHm}"/>
        <c:set var="uMap" value="${requestScope.DAO.userHm}"/>
        <c:set var="qMap" value="${requestScope.DAO.questHm}"/>
        <c:set var="rMap" value="${requestScope.DAO.roleHm}"/>

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
                    <c:if test="${sessionScope.UserLogined == null}">
                        <div class="btn-group">
                            <button type="img" class="btn btn-dark"> <img src="images/avatar.png" class="rounded-circle"
                                                                          height="22" alt="Avatar" loading="lazy" /></button>
                            <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>

                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/LoginController">Login</a>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/RegisterController">Register</a>
                                
                            </div>
                        </div>
                    </c:if>
                     <c:if test="${sessionScope.UserLogined != null && sessionScope.UserLogined.getRoleId()!=1}">
                        <div class="btn-group">
                            <button type="img" class="btn btn-dark"> <img src="${sessionScope.UserLogined.getAvatar()}" class="rounded-circle"
                                                                          height="22" alt="Avatar" loading="lazy" /></button>
                            <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>

                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/UserController?UserType=1">Edit Account</a>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/NewsController?type=2&id=${sessionScope.UserLogined.getId()}">Your Articles</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/LogoutController">Logout</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.UserLogined != null && sessionScope.UserLogined.getRoleId()==1}">
                        <div class="btn-group">
                            <button type="img" class="btn btn-dark"> <img src="${sessionScope.UserLogined.getAvatar()}" class="rounded-circle"
                                                                          height="22" alt="Avatar" loading="lazy" /></button>
                            <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>

                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/UserController?UserType=0">Edit User</a>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/CateUpdate">Edit Category</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/LogoutController">Logout</a>
                            </div>
                        </div>
                    </c:if>
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
                <div class="container col-md-12 col-md-offset-3" style="overflow: auto">
                    <div class="card-body p-4 p-lg-5 text-black">
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Avatar</th>
                                    <th scope="col">Full name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Gender</th>
                                    <th scope="col">DOB</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Role</th>
                                    <th scope="col">Active</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>

                                </tr>
                            </thead>    
                            <tbody>
                                <c:forEach items="${uMap}" var="user">
                                    <c:if test="${user.value.roleId != 1}">
                                        <tr>
                                            <th scope="row">${user.value.id}</th>
                                            <td><img src="${user.value.avatar}" class="rounded-circle"
                                                     height="35" alt="Avatar" loading="lazy" /></td>
                                            <td>${user.value.fullname}</td>
                                            <td>${user.value.email}</td>
                                            <td>${(user.value.gender?'Male':'Female')}</td>
                                            <td>${user.value.dob}</td>
                                            <td>${user.value.address}</td>
                                            <td>${rMap.get(user.value.roleId).getRole()}</td>
                                            <td><img src="${user.value.active?'images/active.png':'images/inactive.jpg'}" class="rounded-circle"
                                                     height="25" /></td>
                                            <td><a href="UserController?userId=${user.value.id}&UserType=0" class="btn btn-secondary active" role="button" aria-pressed="true">Edit</a></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
                    <div class="card-body p-4 p-lg-5 text-black">
                        <form action='UserController' method='post'>

                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Id</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" placeholder="Id" name="userId" value="${requestScope.user.id}">
                                </div>
                            </div>

                            <fieldset class="form-group">
                                <div class="row">
                                    <legend class="col-form-label col-sm-2 pt-0">Role</legend>

                                    <div class="col-sm-10">
                                        <c:forEach items="${rMap}" var="role">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="roleId" value="${role.value.id}" ${requestScope.user.roleId == role.value.id?"checked":""} >  ${role.value.getRole()}                          
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="form-group">
                                <div class="row">
                                    <legend class="col-form-label col-sm-2 pt-0">State</legend>
                                    <div class="col-sm-10">

                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="active" value="on" ${requestScope.user.active?"checked":""}> Active
                                        </div>

                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="active" value="off" ${requestScope.user.active?"":"checked"}> Inactive
                                        </div>

                                    </div>
                                </div>
                            </fieldset>


                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                </div>
                            </div>
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


