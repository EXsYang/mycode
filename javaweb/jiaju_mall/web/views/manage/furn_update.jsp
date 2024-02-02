<%--修改为jsp文件头--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <%--localhost:8080/jiaju_mall/views/manage/furn_update.jsp--%>
    <%--添加base标签--%>
    <base href="<%=request.getContextPath() + "/"%>">


    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">

    <style>

        <%--设置父级div元素 为相对定位 子元素绝对定位 此时 子元素的绝对定位将会参考父元素
        子绝父相--%>
        #pic{
            position: relative;
        }

        input[type="file"] {
            position: absolute;
            left: 0;
            top: 0;
            height: 150px;
            /*opacity: 0，表示完全透明 设置后 丑陋的"浏览..." 文字就看不到了！*/
            opacity: 0;

            /*background-color: red;*/

            /*cursor: pointer
            是指当鼠标移动到元素上时,鼠标指针的形状会发生变化,变成一只手,表示该元素可以被点击。*/
            cursor: pointer;
        }




    </style>

    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {


            // input 标签元素 不可以绑定表单提交事件！！！
            //$(".update").submit(function (){
            //    console.log("ook")
            //    return false;
            //})


            // 给点击图片的a标签 绑定点击事件
            $("#change_a").click(function () {
                alert("ok");
                //点击a 标签 不可以跳转 而是修改图片
                //模拟点击事件 点击file input 标签
                //$(".file_input").click(function () {
                //
                //})

                alert("图片外的a标签 不跳转")

                //绑定一个 onchange 事件 内容改变就触发


                return false;  //不跳转


            })

            //使用jquery 也可以绑定表单提交事件 submit
            //$("#update_form").submit(function () {
            //    alert("jquery 绑定表单提交事件 submit")
            //
            //
            //
            //
            //    //不提交
            //    //return false;
            //})






        })

        // 注意 是给form表单绑定onsubmit事件 而不是给form表单中的input元素绑定onsubmit事件！！！

        //动态注册表单提交事件
        //window.onload = function () {
        //    //使用折半法, 观察原页面是否真的是最新的, 是不是修改的页面和访问的页面一致
        //    //得到 from2 表单dom对象
        //    var form2 = document.getElementById("update_form");
        //
        //    // //给form2绑定onsubmit事件
        //    // 老韩解释 onsubmit 绑定的函数，会直接将结果(f,t)返回给onsubmit
        //    form2.onsubmit = function () {
        //            alert("不提交");
        //            return false;//不提交
        //    }
        //}


        $(function (){
            // 不可以放在 本页面加载完后的函数中 会导致下面的函数prev 失效 没有调用过它
            // 注意函数的定义一定要放在调用之前！！ 放在这里 会导致页面加载完后才定义此函数
            // 放在此$(function (){}函数 外面是按照从上到下执行的 ！
            function prev(event) {
                //获取展示图片的区域
                var img = document.getElementById("prevView");
                //获取文件对象
                var file = event.files[0];
                //获取文件阅读器： Js的一个类，直接使用即可
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    //给img的src设置图片url
                    img.setAttribute("src", this.result);
                }
            }
        })


        // 不可以放在 本页面加载完后的函数中    注意函数的定义一定要放在调用之前！！
        /*大多数 JavaScript 程序都包含许多 JavaScript 语句。
          这些语句会按照它们被编写的顺序逐一执行。*/
        function prev(event) {
            //获取展示图片的区域
            var img = document.getElementById("prevView");
            //获取文件对象
            var file = event.files[0];
            //获取文件阅读器： Js的一个类，直接使用即可
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                //给img的src设置图片url
                img.setAttribute("src", this.result);
            }
        }

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
                        <a href="index.jsp"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">

                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="#">家居管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">订单管理</a>
                        </div>
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
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
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
<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">家居后台管理-修改家居</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <%--<form action="manage/furnServlet" method="post">--%>
                <%--<form action="manage/furnServlet" method="post" enctype="application/x-www-form-urlencoded">--%>
                <%----%>
                <%--<form action="manage/furnServlet" method="post"  enctype="multipart/form-data">--%>



                    <%--//2 老师说明: 是在FurnServlet showFurn()请求转发过来的 可以通过 param.pageNo 获取参数！！！--%>
                <form action="manage/furnServlet?action=update&id=${requestScope.furn.id}&pageNo=${param.pageNo}" method="post"  enctype="multipart/form-data">

                    <%--自己一定要多思考，多看代码, 多动手, 因为是post,所以我们需要使用到隐藏域id,action--%>
                    <%--注意：下面的写法form表单中的action=update会被拿掉，是错误的 即使是get请求也会被拿掉--%>
                    <%--<form action="manage/furnServlet?action=update" method="get">--%>
                    <%--通过反射确定调用servlet的update方法--%>

                    <%--<input type="hidden" name="action" value="update">--%>
                    <%--传id--%>
                    <%--<input type="hidden" name="id" value="${requestScope.furn.id}">--%>
                    <%--传pageNo--%>
                    <%--<input type="hidden" name="pageNo" value="${param.pageNo}">--%>


                    <%--自己写的 正确的 用于修改后回到 点击修改的那个页面--%>
                    <%--因为是将page对象设置在了session域对象的空间中 所以在不同页也可以进行获取
                    显示的是哪一页 page方法设置到session中的page对象就是那一页的page对象 因此从这一页跳转
                    到其他页面进行访问 都是访问的显示出来的这一页的page对象的信息--%>

                    <%--传sessionScope page.pageNO--%>
                    <%--<input type="hidden" name="pageNo" value="${sessionScope.page.pageNo}">--%>
                    <%--传sessionScope page.pageSize--%>
                    <%--<input type="hidden" name="pageSize" value="${sessionScope.page.pageSize}">--%>

                    <%--老韩的思路--%>

                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>商家</th>
                                <th>价格</th>
                                <th>销量</th>
                                <th>库存</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="product-thumbnail">

                                    <%--家居图1: <img src="2.jpg" alt="" width="200" height="200" id="prevView">--%>
                                    <%--<input type="file" name="pic" id="" value="" onchange="prev(this)"/>--%>

                                    <%--这里的img 就是要展示的图片！ 类似于家具图1的作用--%>
                                    <%--<a href="#" id="change_a"><img class="img-responsive ml-3" id="prevView"--%>
                                    <%--                               src="${requestScope.furn.imgPath}"--%>
                                    <%--                               alt=""/>--%>
                                    <%--    <input type="file" name="pic" id="file_input" class="file_input" value=""--%>
                                    <%--           onchange="prev(this)"/>--%>
                                    <%--</a>--%>
                                        <div id="pic">
                                            <img class="img-responsive ml-3" id="prevView"
                                                 src="${requestScope.furn.imgPath}"
                                                 alt=""/>
                                            <input type="file" name="imgPath" id="file_input" class="file_input" value="${requestScope.furn.imgPath}"
                                                   onchange="prev(this)"/>
                                        </div>



                                </td>
                                <%--<input name="id" style="width: 90%" type="hidden" value="${requestScope.furn.id}"/>--%>
                                <td class="product-name"><input name="name" style="width: 60%" type="text"
                                                                value="${requestScope.furn.name}"/></td>
                                <td class="product-name"><input name="maker" style="width: 90%" type="text"
                                                                value="${requestScope.furn.maker}"/></td>
                                <td class="product-price-cart"><input name="price" style="width: 90%" type="text"
                                                                      value="${requestScope.furn.price}"/></td>
                                <td class="product-quantity">
                                    <input name="sales" style="width: 90%" type="text"
                                           value="${requestScope.furn.sales}"/>
                                </td>
                                <td class="product-quantity">
                                    <input name="stock" style="width: 90%" type="text"
                                           value="${requestScope.furn.stock}"/>
                                </td>
                                <td>
                                    <!--                                    <a href="#"><i class="icon-pencil"></i></a>-->
                                    <!--                                    <a href="#"><i class="icon-close"></i></a>-->
                                    <input type="submit" id="update" class="update"
                                           style="width: 90%;background-color: silver;border: silver;border-radius: 20%;"
                                           value="修改家居"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
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