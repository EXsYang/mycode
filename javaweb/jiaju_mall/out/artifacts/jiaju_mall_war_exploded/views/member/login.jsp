<%--jsp文件头--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>

    <!--增加base标签，之后再进行优化 -->
    <%--<base href="http://localhost:8080/jiaju_mall/">--%>
    <%--<base href="/jiaju_mall/">--%>
    <%--request.getContextPath() == /jiaju_mall --%>
    <base href= <%=request.getContextPath() +"/"%>>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
    <!-- 老师先使用临时方案，后面我们在修改  ctrl+home 页面最上 ctrl+end 页面最下-->
    <!--    引入jquery-->
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {//页面加载完毕后执行function

            // 给注册用户输入框 绑定失去焦点事件 用于发送ajax请求 验证输入的用户是否存在
            $("#username").blur(function () {

                // 下面是自己写的逻辑
                //alert("失去焦点了 blur")
                // 语法： jQuery.getJSON(url,data,success(data,status,xhr))
                //$.getJSON(
                //    "/jiaju_mall/memberServlet",
                //    {
                //        action: "isExistsUsername",
                //        username: $(this).val(),
                //        date: new Date()
                //    },
                //    function (data,status,xhr){
                //        console.log("ajax getJSON请求成功");
                //        // 接收返回来的参数并处理
                //        console.log("member= ",data);
                //        // data 是json对象 => 显示转成json的字符串
                //        if (data.id == -1){
                //            $("span[class='errorMsg']").text("该用户名"+ $("#username").val() +"不存在,可以注册");
                //        }else {
                //            $("span[class='errorMsg']").text("该用户名"+ $("#username").val() +"存在,不可以注册");
                //        }
                //
                //
                //    }
                //)


// 下面是老韩写的逻辑============================
                //获取输入的用户名 js基础
                var username = this.value;
                // 语法： jQuery.getJSON(url,data,success(data,status,xhr))
                //发出ajax请求=》 jquery
                //尽量准确，一把搞定 复制粘贴请求地址
                $.getJSON(
                    //"/jiaju_mall/memberServlet",
                    "memberServlet",
                    //"action=isExistsUsername&username="+username,

                    //相当于发送的ajax请求，携带的数据是通过json对象放入
                    //有些前端小伙伴，喜欢好看
                    {
                        action: "isExistsUsername",
                        username: username,
                        date: new Date()
                    },
                    function (data) {
                        console.log("data= ", data);
                        //data.isExist 注意这里存进去的是一个布尔值！
                        //if (data.isExist == "true"){ // 不是字符串的"true"
                        //if (data.isExist == true){
                        if (data.isExist) {
                            $("span[class='errorMsg']").text("该用户名" + $("#username").val() + "存在,不可以注册");
                        } else {
                            $("span[class='errorMsg']").text("该用户名" + $("#username").val() + "不存在,可以注册");
                        }
                    }
                )
            })


            //决定是显示登录还是注册tab  ""不能少 28行少了会报错 Uncaught ReferenceError: register is not defined
            // 浏览器将el取到的结果register 当作了一个变量处理
            // 类似var register;
            //如果注册失败，显示注册tab,而不是默认的登录tab
            <%--if ( ${requestScope.active} == "register"){ --%>
            if ("${requestScope.active}" == "register") {
                //这里弹窗会导致下面的模拟点击失效
                //alert(dcsdf) //Uncaught ReferenceError: dcsdf is not defined
                <%--console.log(${requestScope.active});--%>
                $("#register_tab")[0].click();//模拟点击
            }


            //对验证码图片进行处理，绑定一个点击事件，可以获取新的验证码
            $("#codeImg").click(function () {
                //很多浏览器 在url没有变化的时候 ，图片不会发出新的请求
                //虽然是无痕浏览但是 因为图片被缓存了 是没办法的
                //浏览器认为返回的就是一个图片 因此请求就不再发出了
                //为了防止不请求 不刷新 可以携带一个变化的参数
                //this.src="http://localhost:8080/jiaju_mall/kaptchaServlet";

                /*下面这种方式携带一个变化的参数可以解决点击后验证码图片不请求 不刷新的问题
                * 但是是写死的 将来域名或端口变化了 就不好使了 要做活一点
                * */
                //this.src="http://localhost:8080/jiaju_mall/kaptchaServlet?d=" + new Date();
                this.src = "<%=request.getContextPath()%>/kaptchaServlet?d=" + new Date();
                //this.src="kaptchaServlet";

                //this.src="kaptchaServlet?d="+new Date();

            })


            //绑定点击事件 => 忘记的去看jquery
            $("#sub-btn").click(function () {
                // <input type="text" name="user-name" placeholder="Username"/>
                //获取到到输入的用户名 => 自己看前端给的页面
                var usernameVal = $("#username").val();
                // alert("usernameVal= " + usernameVal)
                //编写正则表达式来进行验证.
                var usernamePattern = /^\w{6,10}$/;
                //验证
                if (!usernamePattern.test(usernameVal)) {
                    // 进来说明没有匹配上提示一句话
                    //展示错误提示, jquery属性过滤器
                    $("span[class='errorMsg']").text("用户名格式不对, 需要6-10字符");
                    return false;//不提交 , 返回false
                }

                //一关一个关的通过验证
                //完成对密码的校验
                var passwordVal = $("#password").val();
                var passwordPattern = /^\w{6,10}$/;
                if (!passwordPattern.test(passwordVal)) {
                    // 进来说明没有匹配上提示一句话
                    //展示密码错误提示-基本过滤器, 希望小伙伴感到知识不是每个都是新
                    //信心-》潜意识我学过.
                    $("span.errorMsg").text("密码格式不对, 需要6-10字符");
                    return false;
                }

                //两次密码相同
                //得到第二次输入密码
                var repwdVal = $("#repwd").val();
                if (repwdVal != passwordVal) {
                    // 进来说明没有匹配上提示一句话
                    $("span.errorMsg").text("两次密码不相同！!");
                    return false;
                }

                //验证邮箱
                //得到邮箱 => 去看html
                //老师说明 在java中，正则表达式的转义是\\, 在js 正则表达式 转义是\
                //如果你看不懂，回看java正则表达式
                var emailVal = $("#email").val();
                var emailPattern = /^[\w-]+@([a-zA-Z]+\.)+[a-zA-Z]+$/;//偷懒->java
                if (!emailPattern.test(emailVal)) {
                    // 进来说明没有匹配上提示一句话
                    $("span.errorMsg").text("邮箱格式不正确！!");
                    return false;
                }

                //验证验证码
                //验证码不可以为空的前端验证
                //这里出现 怎么获取都为空的问题 原因是input标签没有innerText 这里需要按照value获取
                /*
                    •text() - 设置或返回所选元素的文本内容
                    •html() - 设置或返回所选元素的内容（包括 HTML 标记）
                    •val() - 设置或返回表单字段的值
                */
                //var codeText = $("#code").text();
                var codeText = $("#code").val();
                // 去掉验证码前后空格
                codeText = $.trim(codeText);
                /*2、实用工具函数，作为几个通用的实用工具函数的命名空间的前缀
                    例如：
                    $.trim(someString)*/
                //alert(codeText)
                //if ("" == codeText || null == codeText){
                if (codeText == "" || codeText == null) {
                    //提示信息
                    $("span.errorMsg").text("验证码不能为空")
                    return false;
                }


                //到这里就全部过关. => 我们暂时不提交，显示验证通过信息
                $("span.errorMsg").text("验证通过...");
                // console.log("注册成功！！！")
                // alert("注册成功！！！")
                // return false;

                //alert("kk")
                // 写了后端，进行提交
                return true;
            })

            /* // 登录功能
             $("button[type='submit']").click(function (){
             // 登录功能前端校验 暂时不校验
                 alert("ok~")
             })*/

            // 登录界面数据回显功能
            // 错误的思路：当用户点击login按钮时 发送Ajax请求

            // 正确的思路：如果用户名或密码错误 回显信息
            // 这里需要到后端进行校验，也就是需要提交一次 然后将错误的用户名填写到输入框中
            // 将后端返回来的信息显示出来    只需要使用jsp页面的特性，在后端设置request域对象
            // 的属性 在前端通过el表达式中的隐藏域对象<%-- ${requestScope.msg} --%> 或者
            //<!--在jsp中使用request.getParameter("username") 如果username 不存在 会输出null-->  来动态的获取！！


            //$("#login_submit").click(function () {
            //    // 登录回显功能 点击Login button后进行后端校验 通过返回的数据进行 回显
            //    alert("ok~！~");
            //})


            // 如果用户名或密码错误 回显信息
            // 这里需要到后端进行校验，也就是需要提交一次 然后将错误的用户名填写到输入框中

            // 将后端返回来的信息显示出来    只需要使用jsp页面的特性，在后端设置request域对象
            // 的属性 在前端通过el表达式中的隐藏域对象<%-- ${requestScope.msg} --%>来动态的获取！！

            //----------------------------------------------------------------------------
            <%--            <%--%>
            <%--            // 取出request域中的错误信息--%>
            <%--            String error_username = request.getParameter("error_username");--%>
            <%--            if (error_username != null){--%>
            <%--                //说明后端登录失败 将用户名填写在用户名输入框中--%>

            <%--                //--%>
            <%--                //并提示 回显信息 用户名或密码错误--%>
            <%--                %>--%>
            <%--            $("span.errorMsg").text("用户名或密码错误");--%>

            <%--            $("#login_username").val(<%=request.getParameter("error_username")%>);--%>
            <%--            &lt;%&ndash;$("#login_username").val(${requestScope.error_username});&ndash;%&gt;--%>
            <%--            $("#login_password").val(<%=request.getParameter("error_password")%>);--%>
            <%--            return false;--%>
            <%--            <%--%>
            <%--             }else{--%>
            <%--            %>--%>
            <%--            // 说明request域中的error_username 为空 没有设置过  也可能是 提交以前！同样--%>
            <%--            //也没有设置过    需要判断的是提交以后的结果   要想得到提交以后的结果就需要表单提交为true--%>
            <%--            // 或者是使用ajax 请求的方式进行处理  表单提交的方式完成不了 因为需要在提交之前得到结果 不可行！--%>
            <%--            // 使用ajax请求 当失去焦点时/点击提交按钮后 发送ajax请求--%>
            <%--            /*  --%>
            <%--             *  这里的这种需求可以使用jsp 动态获取数据的特性 动态获取request域对象中的数据   --%>
            <%--                使用el表达式 或jsp表达式脚本 动态的获取数据！！ 表达式脚本: &lt;%&ndash; <%=%> &ndash;%&gt; --%>
            <%--             */--%>
            <%--            --%>
            <%--            //$("span.errorMsg").text("验证通过ok...");--%>
            <%--            &lt;%&ndash;$("span.errorMsg").text(<%=request.getContextPath() +"/"%>);&ndash;%&gt;--%>
            <%--            return true;--%>
            <%--            <%--%>
            <%--             }--%>
            <%--             %>--%>


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
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png"
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
                        <a class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>会员登录</h4>
                        </a>
                        <a id="register_tab" data-bs-toggle="tab" href="#lg2">
                            <h4>会员注册</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <%--提示错误信息--%>
                                    <%--                                    ${requestScope.msg}--%>
                                    <span class="errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px; color: gainsboro">
                                        ${requestScope.msg}</span>
                                    <!--前面有base标签-->
                                    <form action="memberServlet" method="post">
                                        <%--增加一个隐藏域input元素--%>
                                        <input type="hidden" name="action" value="login">

                                        <!--在jsp中使用request.getParameter("username") 如果username 不存在 会输出null-->
                                        <!--在jsp中使用el表达式 <%-- ${requestScope.username} --%> 如果username 不存在 会输出空串""-->
                                        <%--<input type="text" id="login_username" value="<%=request.getParameter("username")%>" name="username" placeholder="Username"/>--%>
                                        <input type="text" id="login_username" value="${requestScope.username}"
                                               name="username" placeholder="Username"/>
                                        <input type="password" id="login_password" name="password"
                                               placeholder="Password"/>
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <input type="checkbox"/>
                                                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit" id="login_submit"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <%--提示错误信息--%>
                                    <%--${requestScope.msg}--%>
                                    <span class="errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px; color: gainsboro">
                                        ${requestScope.msg}</span>
                                    <form action="memberServlet" method="post">
                                        <%--增加一个隐藏域input元素--%>
                                        <input type="hidden" name="action" value="register">
                                        <input type="text" id="username" name="username"
                                               value="${requestScope.username}" placeholder="Username"/>
                                        <input type="password" id="password" name="password" placeholder="输入密码"/>
                                        <input type="password" id="repwd" name="repassword" placeholder="确认密码"/>
                                        <input name="email" id="email" value="${requestScope.email}" placeholder="电子邮件"
                                               type="email"/>
                                        <input type="text" id="code" name="code" style="width: 50%"
                                               placeholder="验证码"/>　　<img alt="" id="codeImg" src="kaptchaServlet"
                                                                         style="width: 120px;height:50px">
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>会员注册</span></button>
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