<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>韩顺平教育-家居网购</title>
    <!--http://localhost:8080/jiaju_mall/views/manage/manage_login.html-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <%--<base href="/jiaju_mall/">--%>

    <%--<base href="<%=request.getContextPath()%> + /">不行--%>
    <%--<base href=<%=request.getContextPath()%> + "/">不行--%>
    <%--<base href=${requestScope.} + "/">不行--%>
    <%--<base href="${pageContext.request.contextPath + "/"}">  不行 el表达式内部使用+ 出现问题 --%>
    <%--<base href="${pageContext.request.contextPath}" >  单独写一个el表达式不会报错 但是还是不行 路径不对啊--%>

    <c:set scope="request" var="path" value="/jiaju_mall/"></c:set>
    <%--<h2>用el表达式来取数据：</h2>--%>
    c:set-path的值= ${requestScope.path}  <%-- /jiaju_mall/ --%>
    <base href="${requestScope.path}" >

    <%--<base href='<%=request.getContextPath()%>/'> 可以使用这种--%>
<%--<base href='<%=request.getContextPath()%>/'/> 也可以使用这种形式 最后一个/> 中的/ 不加也没事--%>
<%--    <base href="<%=request.getContextPath() + "/"%>"> "/"要加到表达式脚本的里面才行--%>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>

    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">



    </script>

</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo" /></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>管理员登录</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <%--<form action="adminServlet" method="post">--%>

                                    <%--简单处理一下 将管理员信息 也放入到member表中进行处理--%>
                                    <form action="memberServlet" method="post">


                                        <%--在BasicServlet 进行确认action  走子类AdminServlet管理员登录方法login() 验证--%>
                                        <%--走的是adminServlet 的 login 方法--%>
                                        <input type="hidden" name="action" value="login">

                                        <%--<input type="text" name="admin_name" placeholder="Username"/>--%>
                                        <%--<input type="password" name="admin_pwd" placeholder="Password"/>--%>
                                            <%--为了登录方便 写了如下代码--%>
                                        <%--<input type="text" name="admin_name" placeholder="Username" value="admin"/>--%>
                                        <%--<input type="text" name="admin_pwd" placeholder="Password" value="admin"/>--%>

                                       <%--简单处理一下 将管理员信息 也放入到member表中进行处理--%>
                                        <input type="text" name="username" placeholder="Username" value="admin"/>
                                        <input type="text" name="password" placeholder="Password" value="admin"/>
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <input type="checkbox"/>
                                                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a></li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.html">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2021 韩顺平教育~</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="../../assets/js/vendor/vendor.min.js"></script>
<script src="../../assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="../../assets/js/main.js"></script>
</body>
</html>