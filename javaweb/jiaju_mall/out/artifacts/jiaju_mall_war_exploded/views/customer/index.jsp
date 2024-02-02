<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购~</title>
    <%--http://localhost:8080/jiaju_mall/customerFurnServlet?action=page--%>
    <%--正确书写base--%>
    <base href="<%=request.getContextPath() + "/"%>">

    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">

    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function (){
            // 给添加到购物车button 添加点击事件
            // jQuery选择器返回的是一个集合，将所有的添加按钮绑定了点击事件
            //$(".add-to-cart").click(function (){
            //    //alert("ok!")
            //    // 点击后 发送请求
            //    //http://localhost:8080/jiaju_mall/cartServlet?action=addCartItem&id=246
            //    //location.href = "http://localhost:8080/jiaju_mall/cartServlet?action=addItem&id=" + this.value;
            //
            //    var thisCartItemCount = $(this).prev().attr("thisCartItemCount");
            //    // 第一次添加到购物车时 thisCartItemCount=undefined
            //    //alert(thisCartItemCount)
            //
            //    var furnId = $(this).attr("furnId");
            //    //发出一个请求 添加家居 => 后面我们会使用ajax来完成
            //    //location.href = "http://localhost:8080/jiaju_mall/cartServlet?action=addItem&id=" + furnId;
            //
            //    //alert("furnId= " + furnId);
            //    // 目前base路径已经确定了 所以如下这样写
            //    location.href = "cartServlet?action=addItem&id=" + furnId+ "&thisCartItemCount="+thisCartItemCount;
            //
            //
            //})

            // 给添加购物车按钮 绑定点击事件 发送ajax
            $(".add-to-cart").click(function (){
                //alert("ok!")
                var furnId = $(this).attr("furnId");
                // 点击时购物车中该商品的数量
                //var thisCartItemCount = $(this).prev().attr("thisCartItemCount");
                //发出一个请求 添加家居 => 后面我们会使用ajax来完成
                //location.href = "http://localhost:8080/jiaju_mall/cartServlet?action=addItem&id=" + furnId;

                // 语法： jQuery.getJSON(url,data,success(data,status,xhr))
                //老师说明
                //1. 如果你通过jquery发出的ajax请求是get 并且 返回的数据格式是json
                //2. 可以直接使用getJSON() 函数，就很简洁
                //console.log("thisCartItemCount= ",thisCartItemCount)

                //这里我们使用jquery发出ajax请求，得到数据进行局部刷新，解决刷新这个页面的效率低的问题
                //这里我们通过json方式,传入ajax请求要携带的数据
                $.getJSON(
                    "cartServlet",
                    {
                        action:"addItemByAjax",
                        id: furnId,
                        //thisCartItemCount:thisCartItemCount,
                        date:new Date()
                    },
                    function (data){
                        console.log("data= ",data)
                        //span class="header-action-num"
                            if (data.url){
                                // 如果返回的有url 说明没有登录过 前端进行转发
                                location.href=data.url;
                            }else{
                                //$("span.header-action-num").text(data.totalCount);
                                $("span.header-action-num").text(data.cartTotalCount);
                            }

                        //if (data.url == undefined) { //没有返回url ,表示已经登录过.
                        //    //刷新局部 <span class="header-action-num"></span>
                        //    $("span.header-action-num").text(data.cartTotalCount);
                        //} else {
                        //    //说明当前服务器返回url ,要求你定位
                        //    location.href = data.url;
                        //}

                    }
                )


            })



        })

    </script>


</head>

<body>
<!-- Header Area start  -->
<div class="header section">
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

                        <%--下面是自己写的逻辑 正确的--%>
                       <%-- <c:if test="${not empty sessionScope.username}">
                        <div class="header-bottom-set dropdown">
                            <a href="views/member/login.jsp">欢迎${sessionScope.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="views/member/login.jsp">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">安全退出</a>
                        </div>
                        </c:if>

                        <c:if test="${empty param.username && empty sessionScope.username}">
                        &lt;%&ndash; <c:if test="${param.username != null}">&ndash;%&gt;
                        <div class="header-bottom-set dropdown">
                            <a href="views/member/login.jsp">登录|注册</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">后台管理</a>
                        </div>
                        </c:if>--%>

                        <%--下面是老韩写的逻辑--%>
                        <%--
                        根据用户登录状态，显示不同的菜单
                        根据session 有没有member对象来判断

                        --%>

                        <c:if test="${empty sessionScope.member}">
                            <%-- <c:if test="${param.username != null}">--%>
                            <div class="header-bottom-set dropdown">
                                <a href="views/member/login.jsp">登录|注册</a>
                            </div>
                            <div class="header-bottom-set dropdown">
                                <a href="#">后台管理</a>
                            </div>
                        </c:if>

                        <c:if test="${not empty sessionScope.member}">
                        <div class="header-bottom-set dropdown">
                            <a>欢迎${sessionScope.member.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="orderServlet?action=orderManage&memberId=${sessionScope.member.id}">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="memberServlet?action=logout">安全退出</a>
                        </div>
                        </c:if>



                        <!-- Single Wedge End -->
                        <%--老师解读
                           1. 通过我们的分析 offcanvas-toggle 会在main.js 中做处理, 阻止本身超链接的跳转
                           2. 所以我们可以将该 offcanvas-toggle 去掉, 恢复超链接的本身机制
                           3. 说明; 编程的思想和方式非常重要
                       --%>
                        <a href="views/cart/cart.jsp" class="header-action-btn header-action-btn-cart offcanvas-toggle pr-0">

                        <%--将offcanvas-toggle 拿掉后可以正常跳转了--%>
                        <a href="views/cart/cart.jsp" class="header-action-btn header-action-btn-cart pr-0">
                            <i class="icon-handbag"> 购物车</i>
                            <%--${sessionScope.cart.totalCount} 本质是调用cart对象的getTotalCount()方法--%>
                            <%--<span class="header-action-num">${sessionScope.cart.totalCount}</span>--%>
                            <span class="header-action-num"></span>
                        </a>
                        <%--<a href="views/manage/furn_add.jsp">views/manage/furn_add.jsp</a> 正常的a标签可以跳转--%>
                        <a href="#offcanvas-mobile-menu"
                           class="header-action-btn header-action-btn-menu offcanvas-toggle d-lg-none">
                            <i class="icon-menu"></i>
                        </a>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  End -->
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
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
<br/>
<!-- Header Area End  -->

<!-- OffCanvas Cart Start 弹出cart -->
<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start 处理手机端 -->
<!-- OffCanvas Menu End -->

<!-- Product tab Area Start -->
<div class="section product-tab-area">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="tab-content">
                    <!-- 1st tab start -->
                    <div class="tab-pane fade show active" id="tab-product-new-arrivals">
                        <div class="row">

<%--                            <c:forEach items="${requestScope.page.items}" begin="0" end="0" var="furn">--%>
                            <c:forEach items="${requestScope.page.items}" var="furn">
                         <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 mb-6" data-aos="fade-up"
                                 data-aos-delay="200">
                                <!-- Single Prodect -->
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="shop-left-sidebar.html" class="image">
                                                   <%-- &lt;%&ndash;下面是显示的图片&ndash;%&gt;--%>
                                                <img src="${furn.imgPath}" alt="Product"/>
                                                  <%--  &lt;%&ndash;下面是悬停图片&ndash;%&gt;--%>
                                                <img class="hover-image" src="${furn.imgPath}"
                                                     alt="Product"/>
                                            </a>
                                            <span class="badges">
                                                <span class="new">New</span>
                                            </span>
                                            <div class="actions">
                                                <a href="#" class="action wishlist" data-link-action="quickview"
                                                   title="Quick view" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal"><i
                                                        class="icon-size-fullscreen"></i></a>
                                            </div>
                                            <%--<button title="Add To Cart" value="${furn.id}" class="add-to-cart">Add--%>

                                            <%--只有库存大于0才可以添加到购物车 否则不能进行添加并显示暂时缺货--%>

                                            <%--遍历购物车中的商品 --%>
                                            <%--缺货的商品(库存为0) 不会被放入到购物车 在后端会直接返回到本页面
                                            所以一直会返回thisCartItemCount=undefined 后端会一直默认给一个0 然后进入到后端cartServlet addItem的 if
                                            --%>
                                            <c:forEach items="${sessionScope.cart.items}" var="item">
                                                <c:if test="${item.key == furn.id}">
                                                    <%--<button title="thisCartItemCount" thisCartItemCount="${item.value.count}" class="add-to-car">购物车中此商品的个数：${item.value.count}--%>
                                                    <%--</button>--%>
                                                </c:if>
                                            </c:forEach>

                                            <c:if test="${furn.stock > 0}">
                                                <button title="Add To Cart" furnId="${furn.id}" class="add-to-cart">Add
                                                    To Cart
                                                </button>
                                            </c:if>
                                            <c:if test="${furn.stock <= 0}">
                                                <button title="Add To Cart" furnId="${furn.id}" class="add-to-cart"> 暂时缺货
                                                </button>
                                            </c:if>


                                        </div>
                                        <div class="content">
                                            <h5 class="title">
                                                <a href="shop-left-sidebar.html">${furn.name} </a></h5>
                                            <span class="price">
                                                <span class="new">家居:　${furn.name}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">厂商:　${furn.maker}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">价格:　￥${furn.price}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">销量:　${furn.sales}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">库存:　${furn.stock}</span>
                                            </span>
                                        </div>
                                    </div>
                            </div>
                            </c:forEach>

                          <%--  <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 mb-6" data-aos="fade-up"
                                 data-aos-delay="400">
                                <!-- Single Prodect -->
                                <c:forEach items="${requestScope.page.items}" begin="1" end="1" var="furn">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="shop-left-sidebar.html" class="image">
                                                <img src="${furn.imgPath}" alt="Product"/>
                                                <img class="hover-image" src="assets/images/product-image/3.jpg"
                                                     alt="Product"/>
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-10%</span>
                                            <span class="new">New</span>
                                            </span>
                                            <div class="actions">
                                                <a href="#" class="action wishlist" data-link-action="quickview"
                                                   title="Quick view" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal"><i
                                                        class="icon-size-fullscreen"></i></a>
                                            </div>
                                            <button title="Add To Cart" class=" add-to-cart">Add
                                                To Cart
                                            </button>
                                        </div>
                                        <div class="content">
                                            <h5 class="title">
                                                <a href="shop-left-sidebar.html">${furn.name} </a></h5>
                                            <span class="price">
                                                <span class="new">家居:　${furn.name}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">厂商:　${furn.maker}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">价格:　￥${furn.price}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">销量:　${furn.sales}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">库存:　${furn.stock}</span>
                                            </span>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- Single Prodect -->
                            </div>


                            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 mb-es-30px" data-aos="fade-up"
                                 data-aos-delay="600">
                                <!-- Single Prodect -->
                                <c:forEach items="${requestScope.page.items}" begin="2" end="2" var="furn">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="shop-left-sidebar.html" class="image">
                                                <img src="${furn.imgPath}" alt="Product"/>
                                                <img class="hover-image" src="assets/images/product-image/13.jpg"
                                                     alt="Product"/>
                                            </a>
                                            <span class="badges">
                                            </span>
                                            <div class="actions">
                                                <a href="#" class="action wishlist" data-link-action="quickview"
                                                   title="Quick view" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal"><i
                                                        class="icon-size-fullscreen"></i></a>
                                            </div>
                                            <button title="Add To Cart" class=" add-to-cart">Add
                                                To Cart
                                            </button>
                                        </div>
                                        <div class="content">
                                            <h5 class="title">
                                                <a href="shop-left-sidebar.html">${furn.name} </a></h5>
                                            <span class="price">
                                                <span class="new">家居:　${furn.name}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">厂商:　${furn.maker}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">价格:　￥${furn.price}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">销量:　${furn.sales}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">库存:　${furn.stock}</span>
                                            </span>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>


                            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 " data-aos="fade-up" data-aos-delay="800">
                                <!-- Single Prodect -->

                                <c:forEach items="${requestScope.page.items}" begin="3" end="3" var="furn">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="shop-left-sidebar.html" class="image">
                                                <img src="${furn.imgPath}" alt="Product"/>
                                                <img class="hover-image" src="assets/images/product-image/15.jpg"
                                                     alt="Product"/>
                                            </a>
                                            <span class="badges">
                                                <span class="new">New</span>
                                            </span>
                                            <div class="actions">
                                                <a href="#" class="action wishlist" data-link-action="quickview"
                                                   title="Quick view" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal"><i
                                                        class="icon-size-fullscreen"></i></a>
                                            </div>
                                            <button title="Add To Cart~" class=" add-to-cart">Add
                                                To Cart
                                            </button>
                                        </div>
                                        <div class="content">
                                            <h5 class="title">
                                                <a href="shop-left-sidebar.html">${furn.name} </a></h5>
                                            <span class="price">
                                                <span class="new">家居:　${furn.name}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">厂商:　${furn.maker}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">价格:　￥${furn.price}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">销量:　${furn.sales}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">库存:　${furn.stock}</span>
                                            </span>
                                        </div>
                                    </div>
                                </c:forEach>&ndash;%&gt;--%>
                                <!-- Single Prodect -->
                            </div>


                        </div>
                    </div>
                    <!-- 1st tab end -->
                </div>
            </div>
        </div>
    </div>
</div>
<!--  Pagination Area Start -->


<%--下面是自己写的搜索 分页导航的内容
    后端通过设置request域对象中是否有name属性区分 此次请求是否为搜索框的分页请求
  //将搜索的家居名带给前端 如果有此属性说明 是通过家居名搜索得到的分页
  //req.setAttribute("name", name);
 --%>
<%--<c:if test="${empty requestScope.name}">
    &lt;%&ndash;进到这里说明是普通的分页！&ndash;%&gt;
<div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
    <ul>
        <li><a href="customerFurnServlet?action=page&pageNo=1">首页</a></li>
        <c:if test="${requestScope.page.pageNo > 1}">
            <li><a href="customerFurnServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上页</a></li>
        </c:if>
        &lt;%&ndash;        <c:choose>&ndash;%&gt;
        &lt;%&ndash;            <c:when test=""></c:when>&ndash;%&gt;
        &lt;%&ndash;        </c:choose>&ndash;%&gt;
        <c:set scope="request" var="begin" value="1"></c:set>
        <c:set scope="request" var="end" value="${requestScope.page.pageTotalCount}"></c:set>
        &lt;%&ndash;spacing :间隔
           定义一个间隔 用来记录[pageNo+2 - 总页码] 的间隔数
           &ndash;%&gt;
        <c:set scope="request" var="spacing"
               value="${requestScope.page.pageNo + 2 - requestScope.page.pageTotalCount}"></c:set>

        &lt;%&ndash;1.如下方式是把所有的页数都显示出来 当前页数等于循环次数高亮显示
        循环次数就是显示出来的页码数！begin从1开始
        &ndash;%&gt;
        &lt;%&ndash;<c:forEach begin="${begin}" end="${end}" var="i">
            &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
            <c:if test="${requestScope.page.pageNo == i}">
                <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
            </c:if>
            &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
            <c:if test="${requestScope.page.pageNo != i}">
                <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
            </c:if>
        </c:forEach>&ndash;%&gt;

        &lt;%&ndash;2.如下方式是 最多显示5页 当前页数等于 循环的页码数时高亮显示&ndash;%&gt;
        <c:choose>
            &lt;%&ndash;当页数小于等于5页时 全部显示出来
            因为小于5页 不需要使用下面的规则 只有大于5页时才会用到 最少显示5页的规则
            &ndash;%&gt;
            <c:when test="${requestScope.page.pageTotalCount <= 5}">


                        <c:forEach begin="${begin}" end="${end}" var="i">
                        &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;

                        &lt;%&ndash;如果request域中 有name 就取出来 将分页导航指向action=pageByName&ndash;%&gt;
                        &lt;%&ndash;如果 在request域中没有name属性 说明只是普通的分页
                            如果 在request域中有name属性 说明进行的是按照家居名分页！
                        &ndash;%&gt;

                        <c:if test="${requestScope.page.pageNo == i}">
                            <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                        </c:if>
                        &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo != i}">
                            <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                        </c:if>
                        </c:forEach>
            </c:when>

            &lt;%&ndash;当前页码小于3总页数大于5页时 即当前页码减2出现小于1的情况时
                需要另外考虑所使用的规则 : 显示出 1 到 5 页的内容 并将当前页相对应的页码数字高亮显示
            &ndash;%&gt;
            <c:when test="${requestScope.page.pageNo - 2 < 1 && requestScope.page.pageTotalCount > 5}">
                <c:forEach begin="${begin}" end="5" var="i">
                    &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                    &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>

            &lt;%&ndash;当前页码大于等于3 总页数大于5页时 即当前页码减2 不会出现小于1的情况 pageNo - 2 >= 1
                并且同时满足end=pageNo+2 <= 总页数时 可以使用下面的规则
                使用的规则 : 显示出 begin=pageNo-2 到 end=pageNo+2 页的内容 并将当前页相对应的页码数字高亮显示
                注意：使用上述规则时 保证条件中的页码都可以取到 即限制过两头的条件 即
                保证 begin 和 end 为数据库中存在的页码 否则后端进行反射
                因为后端使用了计算begin的公式 int begin = (pageNo - 1) * pageSize;
                所以 在前端获取第1页 其实后端进行查询 是从begin=0 开始的 即获取第0页时
                后端计算的数据库分页查询语句的begin就会出现负数！！
                所以前端需要获取存在的页码数！即传递过去的pageNo=${i} 需要大于0！

            &ndash;%&gt;
            <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2 <= end}">
                &lt;%&ndash;下面的开始与结束条件控制住了 保证一共有5页 i的取值从begin到end&ndash;%&gt;
                <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                    &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                    &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>

            &lt;%&ndash;当前页码大于等于3 总页数大于5页时 即当前页码减2 不会出现小于1的情况 pageNo - 2 >= 1
                并且此时的end=pageNo+2 > 总页数时 可以使用下面的规则
                使用的规则 : 显示出 begin=pageNo-2 到 end=pageNo+2 页的内容 并将当前页相对应的页码数字高亮显示
                不能满足需要 因为后面出现了不存在的页码 所以需要 开始和结束的条件需要更改
                根据此时pageNo+2 超出总页码的值 spacing, 将两端同时减去此间隔spacing
                问题得到解决 相当于两端同时减去 同一个值 显示的总页数5 不会改变 只是当前页高亮的页码向后移动
            &ndash;%&gt;
            <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2  > end}">
                &lt;%&ndash;下面的开始与结束 保证一共有5页 i的取值从begin到end&ndash;%&gt;
                <c:forEach begin="${requestScope.page.pageNo - 2 - spacing}"
                           end="${requestScope.page.pageNo + 2 - spacing}" var="i">
                    &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                    &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>


        </c:choose>

        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
            <li><a href="customerFurnServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下页</a></li>
        </c:if>
        <li><a href="customerFurnServlet?action=page&pageNo=${requestScope.page.pageTotalCount}">末页</a></li>
        <li><a>共${requestScope.page.pageTotalCount}页</a></li>
        <li><a>共${requestScope.page.totalRow}记录</a></li>

    </ul>
</div>
</c:if>--%>

<%--<c:if test="${not empty requestScope.name}">
    &lt;%&ndash;进到这里说明是按照家居名分页！&ndash;%&gt;
    <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
        <ul>
            <li><a href="customerFurnServlet?action=pageByName&pageNo=1&name=${requestScope.name}">首页</a></li>
            <c:if test="${requestScope.page.pageNo > 1}">
                <li><a href="customerFurnServlet?action=pageByName&pageNo=${requestScope.page.pageNo-1}&name=${requestScope.name}">上页</a></li>
            </c:if>
                &lt;%&ndash;        <c:choose>&ndash;%&gt;
                &lt;%&ndash;            <c:when test=""></c:when>&ndash;%&gt;
                &lt;%&ndash;        </c:choose>&ndash;%&gt;
            <c:set scope="request" var="begin" value="1"></c:set>
            <c:set scope="request" var="end" value="${requestScope.page.pageTotalCount}"></c:set>
                &lt;%&ndash;spacing :间隔
                   定义一个间隔 用来记录[pageNo+2 - 总页码] 的间隔数
                   &ndash;%&gt;
            <c:set scope="request" var="spacing"
                   value="${requestScope.page.pageNo + 2 - requestScope.page.pageTotalCount}"></c:set>

                &lt;%&ndash;1.如下方式是把所有的页数都显示出来 当前页数等于循环次数高亮显示
                循环次数就是显示出来的页码数！begin从1开始
                &ndash;%&gt;
                &lt;%&ndash;<c:forEach begin="${begin}" end="${end}" var="i">
                    &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                    &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>&ndash;%&gt;

                &lt;%&ndash;2.如下方式是 最多显示5页 当前页数等于 循环的页码数时高亮显示&ndash;%&gt;
            <c:choose>
                &lt;%&ndash;当页数小于等于5页时 全部显示出来
                因为小于5页 不需要使用下面的规则 只有大于5页时才会用到 最少显示5页的规则
                &ndash;%&gt;
                <c:when test="${requestScope.page.pageTotalCount <= 5}">


                    <c:forEach begin="${begin}" end="${end}" var="i">
                        &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;

                        &lt;%&ndash;如果request域中 有name 就取出来 将分页导航指向action=pageByName&ndash;%&gt;
                        &lt;%&ndash;如果 在request域中没有name属性 说明只是普通的分页
                            如果 在request域中有name属性 说明进行的是按照家居名分页！
                        &ndash;%&gt;

                        <c:if test="${requestScope.page.pageNo == i}">
                            <li><a class="active" href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                        &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo != i}">
                            <li><a href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                </c:when>

                &lt;%&ndash;当前页码小于3总页数大于5页时 即当前页码减2出现小于1的情况时
                    需要另外考虑所使用的规则 : 显示出 1 到 5 页的内容 并将当前页相对应的页码数字高亮显示
                &ndash;%&gt;
                <c:when test="${requestScope.page.pageNo - 2 < 1 && requestScope.page.pageTotalCount > 5}">
                    <c:forEach begin="${begin}" end="5" var="i">
                        &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo == i}">
                            <li><a class="active" href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                        &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo != i}">
                            <li><a href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                </c:when>

                &lt;%&ndash;当前页码大于等于3 总页数大于5页时 即当前页码减2 不会出现小于1的情况 pageNo - 2 >= 1
                    并且同时满足end=pageNo+2 <= 总页数时 可以使用下面的规则
                    使用的规则 : 显示出 begin=pageNo-2 到 end=pageNo+2 页的内容 并将当前页相对应的页码数字高亮显示
                    注意：使用上述规则时 保证条件中的页码都可以取到 即限制过两头的条件 即
                    保证 begin 和 end 为数据库中存在的页码 否则后端进行反射
                    因为后端使用了计算begin的公式 int begin = (pageNo - 1) * pageSize;
                    所以 在前端获取第1页 其实后端进行查询 是从begin=0 开始的 即获取第0页时
                    后端计算的数据库分页查询语句的begin就会出现负数！！
                    所以前端需要获取存在的页码数！即传递过去的pageNo=${i} 需要大于0！

                &ndash;%&gt;
                <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2 <= end}">
                    &lt;%&ndash;下面的开始与结束条件控制住了 保证一共有5页 i的取值从begin到end&ndash;%&gt;
                    <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                        &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo == i}">
                            <li><a class="active" href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                        &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo != i}">
                            <li><a href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                </c:when>

                &lt;%&ndash;当前页码大于等于3 总页数大于5页时 即当前页码减2 不会出现小于1的情况 pageNo - 2 >= 1
                    并且此时的end=pageNo+2 > 总页数时 可以使用下面的规则
                    使用的规则 : 显示出 begin=pageNo-2 到 end=pageNo+2 页的内容 并将当前页相对应的页码数字高亮显示
                    不能满足需要 因为后面出现了不存在的页码 所以需要 开始和结束的条件需要更改
                    根据此时pageNo+2 超出总页码的值 spacing, 将两端同时减去此间隔spacing
                    问题得到解决 相当于两端同时减去 同一个值 显示的总页数5 不会改变 只是当前页高亮的页码向后移动
                &ndash;%&gt;
                <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2  > end}">
                    &lt;%&ndash;下面的开始与结束 保证一共有5页 i的取值从begin到end&ndash;%&gt;
                    <c:forEach begin="${requestScope.page.pageNo - 2 - spacing}"
                               end="${requestScope.page.pageNo + 2 - spacing}" var="i">
                        &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo == i}">
                            <li><a class="active" href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                        &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNo != i}">
                            <li><a href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${requestScope.name}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                </c:when>


            </c:choose>

            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
                <li><a href="customerFurnServlet?action=pageByName&pageNo=${requestScope.page.pageNo+1}&name=${requestScope.name}">下页</a></li>
            </c:if>
            <li><a href="customerFurnServlet?action=pageByName&pageNo=${requestScope.page.pageTotalCount}&name=${requestScope.name}">末页</a></li>
            <li><a>共${requestScope.page.pageTotalCount}页</a></li>
            <li><a>共${requestScope.page.totalRow}记录</a></li>

        </ul>
    </div>
</c:if>--%>

<%--下面是按老韩的思路写的搜索 分页导航的内容  --%>
<%--进到这里说明是按照家居名分页！--%>
<div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
    <ul>
        <%--<li><a href="customerFurnServlet?action=pageByName&pageNo=1&name=${requestScope.name}">首页</a></li>--%>
        <li><a href="${requestScope.page.url}&pageNo=1">首页</a></li>
        <c:if test="${requestScope.page.pageNo > 1}">
            <li><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上页</a></li>
        </c:if>
        <%--        <c:choose>--%>
        <%--            <c:when test=""></c:when>--%>
        <%--        </c:choose>--%>
        <c:set scope="request" var="begin" value="1"></c:set>
        <c:set scope="request" var="end" value="${requestScope.page.pageTotalCount}"></c:set>
        <%--spacing :间隔
           定义一个间隔 用来记录[pageNo+2 - 总页码] 的间隔数
           --%>
        <c:set scope="request" var="spacing"
               value="${requestScope.page.pageNo + 2 - requestScope.page.pageTotalCount}"></c:set>

        <%--1.如下方式是把所有的页数都显示出来 当前页数等于循环次数高亮显示
        循环次数就是显示出来的页码数！begin从1开始
        --%>
        <%--<c:forEach begin="${begin}" end="${end}" var="i">
            &lt;%&ndash;当前页的pageNo等于循环次数高亮显示！即就是对应的页数&ndash;%&gt;
            <c:if test="${requestScope.page.pageNo == i}">
                <li><a class="active" href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
            </c:if>
            &lt;%&ndash;当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示&ndash;%&gt;
            <c:if test="${requestScope.page.pageNo != i}">
                <li><a href="customerFurnServlet?action=page&pageNo=${i}">${i}</a></li>
            </c:if>
        </c:forEach>--%>

        <%--2.如下方式是 最多显示5页 当前页数等于 循环的页码数时高亮显示--%>
        <c:choose>
            <%--当页数小于等于5页时 全部显示出来
            因为小于5页 不需要使用下面的规则 只有大于5页时才会用到 最少显示5页的规则
            --%>
            <c:when test="${requestScope.page.pageTotalCount <= 5}">


                <c:forEach begin="${begin}" end="${end}" var="i">
                    <%--当前页的pageNo等于循环次数高亮显示！即就是对应的页数--%>

                    <%--如果request域中 有name 就取出来 将分页导航指向action=pageByName--%>
                    <%--如果 在request域中没有name属性 说明只是普通的分页
                        如果 在request域中有name属性 说明进行的是按照家居名分页！
                    --%>

                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                    <%--当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示--%>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>

            <%--当前页码小于3总页数大于5页时 即当前页码减2出现小于1的情况时
                需要另外考虑所使用的规则 : 显示出 1 到 5 页的内容 并将当前页相对应的页码数字高亮显示
            --%>
            <c:when test="${requestScope.page.pageNo - 2 < 1 && requestScope.page.pageTotalCount > 5}">
                <c:forEach begin="${begin}" end="5" var="i">
                    <%--当前页的pageNo等于循环次数高亮显示！即就是对应的页数--%>
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                    <%--当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示--%>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>

            <%--当前页码大于等于3 总页数大于5页时 即当前页码减2 不会出现小于1的情况 pageNo - 2 >= 1
                并且同时满足end=pageNo+2 <= 总页数时 可以使用下面的规则
                使用的规则 : 显示出 begin=pageNo-2 到 end=pageNo+2 页的内容 并将当前页相对应的页码数字高亮显示
                注意：使用上述规则时 保证条件中的页码都可以取到 即限制过两头的条件 即
                保证 begin 和 end 为数据库中存在的页码 否则后端进行反射
                因为后端使用了计算begin的公式 int begin = (pageNo - 1) * pageSize;
                所以 在前端获取第1页 其实后端进行查询 是从begin=0 开始的 即获取第0页时
                后端计算的数据库分页查询语句的begin就会出现负数！！
                所以前端需要获取存在的页码数！即传递过去的pageNo=${i} 需要大于0！

            --%>
            <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2 <= end}">
                <%--下面的开始与结束条件控制住了 保证一共有5页 i的取值从begin到end--%>
                <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                    <%--当前页的pageNo等于循环次数高亮显示！即就是对应的页数--%>
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                    <%--当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示--%>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>

            <%--当前页码大于等于3 总页数大于5页时 即当前页码减2 不会出现小于1的情况 pageNo - 2 >= 1
                并且此时的end=pageNo+2 > 总页数时 可以使用下面的规则
                使用的规则 : 显示出 begin=pageNo-2 到 end=pageNo+2 页的内容 并将当前页相对应的页码数字高亮显示
                不能满足需要 因为后面出现了不存在的页码 所以需要 开始和结束的条件需要更改
                根据此时pageNo+2 超出总页码的值 spacing, 将两端同时减去此间隔spacing
                问题得到解决 相当于两端同时减去 同一个值 显示的总页数5 不会改变 只是当前页高亮的页码向后移动
            --%>
            <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2  > end}">
                <%--下面的开始与结束 保证一共有5页 i的取值从begin到end--%>
                <c:forEach begin="${requestScope.page.pageNo - 2 - spacing}"
                           end="${requestScope.page.pageNo + 2 - spacing}" var="i">
                    <%--当前页的pageNo等于循环次数高亮显示！即就是对应的页数--%>
                    <c:if test="${requestScope.page.pageNo == i}">
                        <li><a class="active" href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                    <%--当前页的pageNo不等于循环次数 即这里显示的不是对应的页数 不高亮显示--%>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <li><a href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>


        </c:choose>

        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
            <li><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下页</a></li>
        </c:if>
        <li><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotalCount}">末页</a></li>
        <li><a>共${requestScope.page.pageTotalCount}页</a></li>
        <li><a>共${requestScope.page.totalRow}记录</a></li>

    </ul>
</div>

<!--  Pagination Area End -->
<!-- Product tab Area End -->

<!-- Banner Section Start -->
<div class="section pb-100px pt-100px">
    <div class="container">
        <!-- Banners Start -->
        <div class="row">
            <!-- Banner Start -->
            <div class="col-lg-6 col-12 mb-md-30px mb-lm-30px" data-aos="fade-up" data-aos-delay="200">
                <a href="shop-left-sidebar.html" class="banner">
                    <img src="assets/images/banner/1.jpg" alt=""/>
                </a>
            </div>
            <!-- Banner End -->

            <!-- Banner Start -->
            <div class="col-lg-6 col-12" data-aos="fade-up" data-aos-delay="400">
                <a href="shop-left-sidebar.html" class="banner">
                    <img src="assets/images/banner/2.jpg" alt=""/>
                </a>
            </div>
            <!-- Banner End -->
        </div>
        <!-- Banners End -->
    </div>
</div>
<!-- Banner Section End -->
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

<!-- Modal 放大查看家居 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">x</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-5 col-sm-12 col-xs-12 mb-lm-30px mb-sm-30px">
                        <!-- Swiper -->
                        <div class="swiper-container gallery-top mb-4">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/1.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/2.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/3.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/4.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/5.jpg" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="swiper-container gallery-thumbs slider-nav-style-1 small-nav">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/1.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/2.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/3.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/4.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/5.jpg" alt="">
                                </div>
                            </div>
                            <!-- Add Arrows -->
                            <div class="swiper-buttons">
                                <div class="swiper-button-next"></div>
                                <div class="swiper-button-prev"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12">
                        <div class="product-details-content quickview-content">
                            <h2>Originals Kaval Windbr</h2>
                            <p class="reference">Reference:<span> demo_17</span></p>
                            <div class="pro-details-rating-wrap">
                                <div class="rating-product">
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                </div>
                                <span class="read-review"><a class="reviews" href="#">Read reviews (1)</a></span>
                            </div>
                            <div class="pricing-meta">
                                <ul>
                                    <li class="old-price not-cut">$18.90</li>
                                </ul>
                            </div>
                            <p class="quickview-para">Lorem ipsum dolor sit amet, consectetur adipisic elit eiusm tempor
                                incidid ut labore et dolore magna aliqua. Ut enim ad minim venialo quis nostrud
                                exercitation ullamco</p>
                            <div class="pro-details-size-color">
                                <div class="pro-details-color-wrap">
                                    <span>Color</span>
                                    <div class="pro-details-color-content">
                                        <ul>
                                            <li class="blue"></li>
                                            <li class="maroon active"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="pro-details-quality">
                                <div class="cart-plus-minus">
                                    <input class="cart-plus-minus-box" type="text" name="qtybutton" value="1"/>
                                </div>
                                <div class="pro-details-cart btn-hover">
                                    <button class="add-cart btn btn-primary btn-hover-primary ml-4"> Add To
                                        Cart
                                    </button>
                                </div>
                            </div>
                            <div class="pro-details-wish-com">
                                <div class="pro-details-wishlist">
                                    <a href="wishlist.html"><i class="ion-android-favorite-outline"></i>Add to
                                        wishlist</a>
                                </div>
                                <div class="pro-details-compare">
                                    <a href="compare.html"><i class="ion-ios-shuffle-strong"></i>Add to compare</a>
                                </div>
                            </div>
                            <div class="pro-details-social-info">
                                <span>Share</span>
                                <div class="social-info">
                                    <ul>
                                        <li>
                                            <a href="#"><i class="ion-social-facebook"></i></a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="ion-social-twitter"></i></a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="ion-social-google"></i></a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="ion-social-instagram"></i></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal end -->

<!-- Use the minified version files listed below for better performance and remove the files listed above -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>