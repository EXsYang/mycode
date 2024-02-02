<!--<!DOCTYPE html>-->
<%--jsp文件头--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <%--<base href="http://localhost:8080/jiaju_mall/">--%>
    <base href="<%=request.getContextPath() + "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>

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
                        <a href="customerFurnServlet?action=pageByName&pageNo=1"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <%--搜索框表单--%>
                                <form class="action-form" action="customerFurnServlet">
                                    <%--反射到customerFurnServlet的pageByName方法--%>
                                    <input type="hidden" name="action" value="pageByName">

                                    <%--增加name=name属性 用于后端获取数据--%>
                                    <input class="form-control" placeholder="Enter your search key" name="name" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <%--<a href="#">欢迎${requestScope.member.username}</a>--%>
                            <%--<a href="#">欢迎${requestScope.member.username}</a>--%>

                            <a>欢迎${sessionScope.member.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="orderServlet?action=orderManage&memberId=${sessionScope.member.id}">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="memberServlet?action=logout">安全退出</a>
                        </div>
                        <!-- Single Wedge End -->
                        <%--<a href="#offcanvas-cart"--%>
                        <%--   class="header-action-btn header-action-btn-cart offcanvas-toggle pr-0">--%>
                        <%--    <i class="icon-handbag"> 购物车</i>--%>
                        <%--    <span class="header-action-num">88</span>--%>
                        <%--</a>--%>
                        <%--<a href="#offcanvas-mobile-menu"--%>
                        <%--   class="header-action-btn header-action-btn-menu offcanvas-toggle d-lg-none">--%>
                        <%--    <i class="icon-menu"></i>--%>
                        <%--</a>--%>
                    </div>
                </div>
                <!-- Header Action End -->

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
                        <a href="customerFurnServlet?action=pageByName&pageNo=1"><img width="280px" src="assets/images/logo/logo.png"
                                                  alt="Site Logo"/></a>
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
                        <%--<a class="active"  href="customerFurnServlet?action=pageByName&pageNo=1&username=${requestScope.member.username}">--%>

                        <%--走我们的网站首页，即项目的web目录下的index.jsp 网站的入口
                        不可以直接访问 customer/index.jsp 因为页面的数据是通过web目录下的index.jsp 网站的入口
                        进行请求转发到customerServlet后准备好的 customer/index.jsp只是一个展示数据的页面
                        页面/文件之间有协调工作过程，要把思路理清--%>
                        <a class="active"  href="index.jsp">

                        <%--下面这个a标签走的是 http://localhost:8080/jiaju_mall/--%>
                        <%--<a class="active"  href=""> --%>

                        <%--下面这个a标签走的是 http://localhost:8080/
                        浏览器把第一个斜杠解析成http://localhost:8080/  具体看webpath专题
                        这里这样写是错误的--%>
                        <%--<a class="active"  href="/">--%>
                            <h4>登录成功, 返回首页</h4>
                        </a>
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
                                        <li class="li"><a class="single-link" href="login.jsp">登录</a></li>
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
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>