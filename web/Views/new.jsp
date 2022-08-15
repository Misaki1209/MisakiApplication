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
        <c:set var="id" value="${requestScope.newId}"/>
        <c:set var="cMap" value="${requestScope.DAO.cateHm}"/>
        <c:set var="uMap" value="${requestScope.DAO.userHm}"/>
        <c:set var="cmtList" value="${requestScope.DAO.cmtList}"/>
        <c:forEach items="${requestScope.DAO.nList}" var="i">
            <c:if test="${i.id==id}">
                <c:set var="st" value="${i}"/>
            </c:if>
        </c:forEach>
        <div id="colorlib-page">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="js-fullheight text-center">
                <h1 id="colorlib-logo"><a href="index.jsp">misaki<span>.</span></a></h1>
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                            <c:forEach items="${requestScope.DAO.cateHm}" var="cate">
                                <c:if test="${st.cateId==cate.value.id}"> 
                                <li class="colorlib-active"><a href='NewsController?type=0&id=${cate.value.id}'>${cate.value.name}</a></li>
                                </c:if>

                            <c:if test="${st.cateId!=cate.value.id}">
                                <li><a href='NewsController?type=0&id=${cate.value.id}'>${cate.value.name}</a></li>
                                </c:if>
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
            </aside> <!-- END COLORLIB-ASIDE -->
            <div id="colorlib-main">

                <section class="ftco-section">
                    <div class="container">
                        <div class="row">



                            <div class="col-md-12">
                                <div class="blog-entry ftco-animate">

                                    <img src="${st.image}" class="img-fluid"/>
                                    <div class="text text-2 pt-2 mt-3">
                                        <span class="category mb-3 d-block"><a href="NewsController?type=0&id=${st.cateId}">${cMap.get(st.cateId).getName()}</a></span>
                                        <h3 class="mb-4"><a href="#">${st.name}</a></h3>
                                        <p class="mb-4">${st.detail}</p>

                                        <div class="author mb-4 d-flex align-items-center">
                                            <a href="#" class="img" style="background-image: url(${uMap.get(st.userId).getAvatar()});"></a>
                                            <div class="ml-3 info">
                                                <span>Written by</span>
                                                <h3><a href="NewsController?type=2&id=${st.userId}">${uMap.get(st.userId).getFullname()}</a>, <span>${st.date}</span></h3>
                                            </div>
                                        </div>
                                        <div class="meta-wrap align-items-center">
                                            <c:if test="${sessionScope.UserLogined != null}">
                                                <div class="half order-md-last">
                                                    <form action="LikeController" method="post">
                                                        <p class="meta">
                                                            <input type="text" hidden name="ArId" value="${st.id}">
                                                            <span><button type="submit" class="btn ${likeArr[st.id]==true?"btn-danger":"btn-light"}" name="likeBtn" value="${likeArr[st.id]==true?0:1}"><span class="icon-heart"></span></button>  ${st.likeNum} </span>
                                                            <span><button type="button" class="btn btn-light"><span class="icon-comment"></span></button>  ${st.cmtNum} </span>

                                                        </p>
                                                    </form>
                                                </div>
                                            </c:if>
                                            <c:if test="${sessionScope.UserLogined == null}">
                                                <div class="half order-md-last">

                                                    <p class="meta">
                                                        <span><i class="icon-heart"></i>  ${st.likeNum} </span>
                                                        <span><i class="icon-comment"></i>  ${st.cmtNum} </span>

                                                    </p>

                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>



                        <div class="container mt-5">
                            <div class="row  d-flex justify-content-center">
                                <div class="col-md-12">
                                    <div class="headings d-flex justify-content-between align-items-center mb-3">
                                        <h5>Comments(${st.getCmtNum()})</h5>
<!--                                        <div class="buttons">

                                            <span class="badge bg-white d-flex flex-row align-items-center">
                                                <span class="text-primary">Comments "ON"</span>
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" checked>
                                                </div>
                                            </span>
                                            <input type="checkbox" checked data-toggle="toggle" data-onstyle="info">
                                        </div>-->
                                    </div>

                                    <c:forEach items="${cmtList}" var="cm">
                                        <c:if test="${cm.newId == id}">
                                            <div class="card p-3">
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <div class="user d-flex flex-row align-items-center">
                                                        <img src="${uMap.get(cm.userId).getAvatar()}" width="30" class="user-img rounded-circle mr-2">
                                                        <span><small class="font-weight-bold text-primary">${uMap.get(cm.userId).getFullname()}</small> <small class="font-weight-bold">${cm.cmt}</small></span>
                                                    </div>
                                                    <c:if test="${cm.userId == sessionScope.UserLogined.getId()}">
                                                        <div class="text-right">
                                                            <form action="CommentController" method="post">
                                                                <input type="text" hidden name="ArId" value="${st.id}">
                                                                <input type="text" hidden name="cmtId" value="${cm.id}">
                                                                <input type="submit" class="btn btn-light btn-sm active" name="editComment1" value="Edit">
                                                                <input type="submit" class="btn btn-light btn-sm active" name="deleComment" value="Dele">
                                                            </form>
                                                        </div>
                                                    </c:if>
                                                    
                                                    <c:if test="${cm.userId != sessionScope.UserLogined.getId() && (sessionScope.UserLogined.getRoleId()==1 || sessionScope.UserLogined.getId() == st.getUserId())}">
                                                        <div class="text-right">
                                                            <form action="CommentController" method="post">
                                                                <input type="text" hidden name="ArId" value="${st.id}">
                                                                <input type="text" hidden name="cmtId" value="${cm.id}">
                                                                <input type="submit" class="btn btn-light btn-sm active" name="deleComment" value="Dele">
                                                            </form>
                                                        </div>
                                                    </c:if>

                                                </div>

                                            </div>

                                        </c:if>
                                    </c:forEach>


                                    <c:if test="${requestScope.editCmt == null && sessionScope.UserLogined != null}">
                                        <form action="CommentController" method="post">
                                            <input type="text" hidden name="addComment" value="addComment">
                                            <input type="text" hidden name="ArId" value="${st.id}">
                                            <div class="d-flex flex-row add-comment-section mt-4 mb-4">
                                                <img src="${sessionScope.UserLogined.getAvatar()}" width="45" class="user-img rounded-circle mr-2">
                                                <input type="text" class="form-control mr-3" placeholder="Add comment" name="comment">
                                                <button class="btn btn-dark" type="submit">Comment</button>
                                            </div>
                                        </form>
                                    </c:if>


                                    <c:if test="${requestScope.editCmt != null}">
                                        <form action="CommentController" method="post">
                                            <input type="text" hidden name="editComment2" value="editComment2">
                                            <input type="text" hidden name="cmtId" value="${requestScope.editCmt.id}">
                                            <input type="text" hidden name="ArId" value="${st.id}">                             
                                            <div class="d-flex flex-row add-comment-section mt-4 mb-4">
                                                <img src="${sessionScope.UserLogined.getAvatar()}" width="45" class="user-img rounded-circle mr-2">
                                                <input type="text" class="form-control mr-3" name="comment" value="${requestScope.editCmt.cmt}">
                                                <button class="btn btn-dark" type="submit">Update</button>
                                            </div>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>




                    </div>
                </section>
                <div class="pt-1 mb-4 text-center">
                    <form action="NewsUpdate" method="post">
                        <input type="text" hidden name="ArticleId" value="${id}">
                        <c:if test="${sessionScope.UserLogined.getId() == st.getUserId()}">
                            <!--                                    <a href="NewsUpdate?type=1&ArticleId=$id" class="btn btn-info btn-lg active" role="button" style="margin: 20px;">Edit</a>
                                                                <a href="NewsUpdate?type=2&ArticleId=$id" class="btn btn-danger btn-lg active" role="button" style="margin: 20px;">Delete</a>-->
                            <input type="submit" class="btn btn-info btn-lg active" name="edit" value="Edit">
                            <input type="submit" class="btn btn-info btn-lg active" name="delete" value="Delete">
                        </c:if>
                        <c:if test="${sessionScope.UserLogined.getId() == st.getUserId() || sessionScope.UserLogined.getRoleId()==1}">
                            <input type="submit" class="btn btn-info btn-lg active"n name="active" value=${st.isActive()?"Inactive":"Active"}>
                        </c:if>
                    </form>

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
