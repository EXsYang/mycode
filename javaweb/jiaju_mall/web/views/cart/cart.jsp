<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <%--走的自己的逻辑--%>
    <%--<script type="text/javascript">--%>

    <%--    $(function (){--%>
    <%--        //页面加载完毕后执行的代码--%>
    <%--        /*------------------------------%>
    <%--      Cart Plus Minus Button--%>
    <%--  ------------------------------ */--%>
    <%--        var CartPlusMinus = $(".cart-plus-minus");--%>
    <%--        CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');--%>
    <%--        CartPlusMinus.append('<div class="inc qtybutton">+</div>');--%>
    <%--        $(".qtybutton").css("background","red")--%>
    <%--        $(".qtybutton").on("click", function() {--%>
    <%--            // 注意这里的$button 选中的是上面的class包含qtybutton的div标签--%>
    <%--            var $button = $(this);--%>
    <%--            // $button.parent()是 $(".cart-plus-minus"); 因为这里使用的是内部插入法！！--%>
    <%--            var oldValue = $button.parent().find("input[class='cart-plus-minus-box']").val();--%>
    <%--            if ($button.text() === "+") {--%>
    <%--                //进到这里 说明点击的是加号--%>
    <%--                //alert("点击的是 +")--%>
    <%--                // 将当前+号所在行的item对象得到--%>
    <%--                // 向后端发送一个(Ajax)请求 让该id的商品项cartItem的数量加1--%>

    <%--                // $button.parent()是 $(".cart-plus-minus"); 因为这里使用的是内部插入法！！--%>
    <%--                // 之前下面的代码不生效 是因为$button.parent() 下面的子元素不包含 属性为class='cartItemId' 的input元素--%>
    <%--                // 之前的input放在了$button.parent() 外面 与其是兄弟元素 所以find不到--%>

    <%--                var $cartItemId = $button.parent().find("input[class='cartItemId']");--%>
    <%--                //var cartItemId = $button.parent().find("input[class='cartItemId']").val();--%>
    <%--                ////var cartItemId = $button.parent().find("input[class='cartItemId']")[0].value;--%>

    <%--                //alert("该商品项id= " + $cartItemId.val())--%>
    <%--                // 将该商品项的id 传给cartServlet 将该项商品总数量加1 该项商品的总价格重新计算--%>
    <%--                // 连带着cart商品总数量+1，cart总价格重新计算--%>

    <%--                location.href = "cartServlet?action=updateItem&update=add&id="+$cartItemId.val();--%>


    <%--               /* 下面这种方式 即直接使用属性选择器 可以取到值--%>
    <%--                var $input = $("input[class='cartItemId']");--%>
    <%--                //var val = $input.val();--%>
    <%--                //alert(val)--%>
    <%--                var inputElement = $input[0];--%>
    <%--                alert("~"+inputElement.value)*/--%>


    <%--                var newVal = parseFloat(oldValue) + 1;--%>


    <%--            } else {--%>
    <%--                //进到这里 说明点击的是减号--%>
    <%--                // alert("点击的是 -")--%>
    <%--                //console.log("点击的是 -~")--%>
    <%--                // 取得是 input 的value 属性的值--%>
    <%--                //var cartItemId = $button.parent().find("input[class='cartItemId']").val();--%>
    <%--                // 下面这个不好使 原因是 之前 $button.parent().下不包含此元素--%>
    <%--                var $cartItemId2 = $button.parent().find("input[class='cartItemId']");--%>


    <%--                //var cartItemId2 = $button.parent().find("input[class='cartItemId']")[0];--%>
    <%--                //var cartItemId2 = $button.parent().find("input[class='cartItemId']")[0].value;--%>
    <%--                //alert("该商品项id= " + $cartItemId2.val())--%>
    <%--                location.href = "cartServlet?action=updateItem&update=sub&id="+$cartItemId2.val();--%>

    <%--                //alert("该商品项的id2 $cartItemId2[0]= " + $cartItemId2[0]) //undefined--%>
    <%--                //alert("该商品项的id2 $cartItemId2.get(0)= " + $cartItemId2.get(0)) //undefined--%>
    <%--                //alert("该商品项的id2= " + cartItemId2)--%>

    <%--                // Don't allow decrementing below zero--%>
    <%--                if (oldValue > 1) {--%>
    <%--                    var newVal = parseFloat(oldValue) - 1;--%>
    <%--                } else {--%>
    <%--                    newVal = 1;--%>
    <%--                }--%>

    <%--            }--%>
    <%--            //$button.parent().find("input").val(newVal);--%>

    <%--            $button.parent().find("input[class=\"cart-plus-minus-box\"]").val(newVal);--%>
    <%--        });--%>


    <%--        // 删除购物车商品项按钮绑定点击事件--%>
    <%--        $("#removeCartItem").click(function (){--%>
    <%--            //var productName = $("td.product-name").text();--%>

    <%--            var productName = $(this).parent().siblings("td.product-name").text();--%>
    <%--            //alert(productName)--%>
    <%--            return window.confirm("你是否要删除商品【"+ productName + "】吗？")--%>


    <%--        })--%>

    <%--        // 清空购物车按钮绑定点击事件--%>
    <%--        $("#clearCart").click(function (){--%>
    <%--            return window.confirm("你是否要清空购物车？")--%>
    <%--        })--%>


    <%--    })--%>




    <%--</script>--%>

    <%--下面是走的老韩的逻辑  自己增加对+-的处理--%>
    <script>
        $(function () {

            //继续购物按钮绑定点击事件
            $("#continue_shopping").click(function (){

                location.href = "index.jsp";
            })


            //给清空购物车绑定一个点击事件
            $("a.clearCart").click(function (){
                //使用确认弹窗
                //返回一个false(取消删除) 或者 true(删除)
                return confirm("你确认要删除购物车?");
            })

            //给删除购物车绑定一个点击事件
            $("a.delItem").click(function () {
                //获取到你要删除的家居名字
                //分析->jquery , 如果忘记了，去看老师讲过的jquery
                //分析一下当前的html结构
                var furnName = $(this).parent().parent().find("td:eq(1)").text();
                //使用确认弹窗
                //返回一个false(取消删除) 或者 true(删除)
                return confirm("你确认要删除【" + furnName + "】?");
            })


            var CartPlusMinus = $(".cart-plus-minus");
            CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
            CartPlusMinus.append('<div class="inc qtybutton">+</div>');
            $(".qtybutton").on("click", function () {

                var $button = $(this);
                var oldValue = $button.parent().find("input").val();
                if ($button.text() === "+") {
                    var newVal = parseFloat(oldValue) + 1;
                } else {
                    // Don't allow decrementing below zero
                    if (oldValue > 1) {
                        var newVal = parseFloat(oldValue) - 1;
                    } else {
                        newVal = 1;
                    }
                }
                $button.parent().find("input").val(newVal);
                var frunId = $button.parent().find("input").attr("furnId");
                //这里我们发出修改购物车的请求
                location.href = "cartServlet?action=updateCount&count=" + newVal + "&id=" + frunId;
            });

        })
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
                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header-bottom-set dropdown">
                            <a>欢迎: ${sessionScope.member.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="orderServlet?action=orderManage&memberId=${sessionScope.member.id}">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">安全退出</a>
                        </div>
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
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png"
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

<!-- OffCanvas Cart Start -->

<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start -->

<!-- OffCanvas Menu End -->


<!-- breadcrumb-area start -->


<!-- breadcrumb-area end -->

<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">Your cart items</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>单价</th>
                                <th>数量</th>
                                <th>金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--显示家居项--%>
                            <%--找到显示购物车项,进行循环items--%>
                            <%--
                                老师分析:
                                1. sessionScope.cart.items => 取出的是HashMap<Integer, CartItem>
                                2. 所以通过foreach取出每一个对象entry是 HashMap<Integer, CartItem> 的 k-v
                                3. var 其实就是 entry => 听不懂的回去看java基础 hashmap
                                4. 所以要取出cartItem 是 通过 entry.value
                            --%>
                            <%--购物车对象cart存在才进行循环显示 --%>
                            <c:if test="${not empty sessionScope.cart}">
                            <%--<c:forEach items="${sessionScope.cart.items}" var="item">--%>
                            <c:forEach items="${sessionScope.cart.items}" var="entry">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="#"><img class="img-responsive ml-3" src="assets/images/product-image/1.jpg"
                                                     alt=""/></a>
                                </td>
                                <%--设置隐藏域 用于记录该商品项的id--%>
                                <%--
                                    input class="cartItemId" 写在这个位置 导致前面js代码不生效 是因为$button.parent() 下面的子元素不包含 属性为class='cartItemId' 的input元素
                                    之前的input放在了$button.parent() 外面 与其是兄弟元素 所以find不到-
                                    -%>
                                <input type="hidden" class="cartItemId" value="${entry.value.id}"/>
                                <%--<input type="hidden" class="cartItemId" value="444"/>--%>


                                <td class="product-name"><a href="#">${entry.value.name}</a></td>
                                <td class="product-price-cart"><span class="amount">${entry.value.price}</span></td>
                                <td class="product-quantity">
                                        <%--商品数量--%>
                                        <%--老师分析,某个js文件对cart-plus-minus做了事件处理--%>
                                    <div class="cart-plus-minus">
                                            <%--走自己的逻辑用的 设置隐藏域 用于记录该商品项的id--%>
                                            <%--<input type="hidden" class="cartItemId" value="${entry.value.id}"/>--%>

                                            <%--<input type="hidden" class="cartItemId" value="444"/>--%>
                                            <%--<input type="text" class="cartItemId" value="444"/>--%>
                                            <%--这里是name=qtybutton 而不是class=qtybutton--%>


                                            <%--<input class="cart-plus-minus-box" type="text" name="qtybutton" value="${entry.value.count}"/>--%>
                                            <input furnId="${entry.value.id}" class="cart-plus-minus-box"
                                                       type="text" name="qtybutton"
                                                       value="${entry.value.count}"/>
                                    </div>
                                </td>
                                <%--购物车中一项的总金额--%>
                                <td class="product-subtotal">${entry.value.totalPrice}</td>
                                <td class="product-remove">
                                    <%--<a id="removeCartItem" href="cartServlet?action=deleteItem&id=${entry.value.id}"><i class="icon-close"></i></a>--%>

                                        <a class="delItem" href="cartServlet?action=delItem&id=${entry.value.id}"><i
                                                class="icon-close"></i></a>
                                </td>
                            </tr>
                            </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper">
                                <h4>共${sessionScope.cart.totalCount}件商品 总价 ${sessionScope.cart.cartTotalPrice}元</h4>
                                <div class="cart-shiping-update">
                                    <a href="orderServlet?action=saveOrder">购 物 车 结 账</a>
                                </div>
                                <div class="cart-clear">
                                    <button id="continue_shopping">继 续 购 物</button>
                                    <%--    <a href="index.jsp">继 续 购 物</a>--%>

                                    <%--<a id="clearCart" href="cartServlet?action=clear">清 空 购 物 车</a>--%>
                                    <a class="clearCart" href="cartServlet?action=clear">清 空 购 物 车</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<!-- Cart Area End -->

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
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>