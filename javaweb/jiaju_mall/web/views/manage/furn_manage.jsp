<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!DOCTYPE html>--%>
<%--修改为jsp文件头--%>
<%--在文件首行添加jstl 的taglib 标签并重新发布项目，否则不识别jstl--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <%--<base href="/jiaju_mall/">--%>
    <base href="<%=request.getContextPath() + "/"%>">
    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">

    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function (){






            // 后台管理表单自动提交 但是不可以跳转 仍然停留在furn_manage 界面
            // 1 可以使用Ajax请求 解决
            // 2 可以使用jsp 动态获取 解决
            // 3 得到的家居信息放入到request域中 请求转发到furn_manage.html

            //$("#form2")[0].submit(); // 前端自动提交，后端提交后进行请求转发或者重定向 循环起来了！？
            //让自动提交只执行一次    设置计数变量  不行 每次转发或者重定向 都会再次加载此页面 count 重新计数！
            //var count = 0;
            //if (count == 0){
            //count++;


            //测试el表达式 是否可以在js 中使用  不可以使用！<%--前端页面没有识别出el表达式 语句就像没写过--%>
            <%--consol+
            e.log(${requestScope.countRequest});--%>
            <%--alert("requestScope.countRequest~",${requestScope.countRequest});--%>


            //根据 后端设置的 计数参数来决定是否再次发送请求
            <%--// ${countRequest} el表达式中的值 在没有请求服务器时还没有进行设置 此时为null 不会进入if 取反可以进去
            http://localhost:8080/jiaju_mall/pages/manager/furn_manage.jsp
                 --%>
            //console.log("$(#count).val()= ",$("#count").val()); // $(#count).val()=  2
            // 如果request域中没有设置过count 属性 $("#count").val()是空字符串 <empty string> 取反可以进去
            // 等提交表单后在返回来时 $("#count").val()不为empty string 此时进不去 下面的if 语句 即每次访问只自动提交一次表单
            // if(!$("#count").val()){
            //    $("#form2")[0].submit(); //自动表单提交
            //}

            //if (!0){
            //    $("#form2")[0].submit();
            //}


            //}

            // el表达式 替代《%=
            <%--           ${requestScope.furns}--%>

            //如何绑定页面加载后通过jstl动态的添加进去的所有的删除a标签呢？ 使用类选择器
            // 这里是jsp页面和jquery/homework2/homework4.html中 绑定初始化的标签 和后添加的标签
            // 有区别 在jsp中动态加载也算在初始化页面信息中
            // 删除_a 标签 要绑定所有的 删除a 标签 此时用类选择器！ jquery类选择器是以集合的形式返回的
            //$("#delete").click(function (){ //这里只有第一个生效！
            // 如何绑定页面加载后通过jstl动态的添加进去的所有的删除a标签呢？ 使用类选择器
            /*下面的这个点击事件是自己使用Ajax 的同步机制 实现的删除操作*/
            $(".delete_a").click(function (){
                //alert("ok")
                //alert($(this).parent().get(1)); // undefined
                //alert($(this).parent().get(0)); // [object HTMLTableCellElement] 是一个dom元素
                //get(0) 获取到的是td dom元素

                //console.log(typeof  $(this).parent().get(1)); //undefined
                //alert($(this).parent().get(1).firstChild().text());
                // 上面.get(1)是undefined 再往后的语句没执行直接跳转了
                //如果是jquery对象, 获取的value
                //如果没有获取到，调用val() , 并不会报错, 对程序的健壮性.
                //var $usernamedd = $("#usernamedd");
                //alert($usernamedd.val())// 这里不会报错， 提示undefined
                //    .css({"color":"red","border":"2px solid red"});

                //parent() 方法返回被选元素的直接父元素。即只有一个元素
                //$(this).parent().parent().eq(0).css("background","yellow"); //该语句生效 选中该行
                //$(this).parent().parent().eq(1).css('background-color', 'blue'); //什么都没输出 parent() 方法返回被选元素的直接父元素。即只有一个元素 找不到数组里的第二个元素

                //使用父类选择器解决 选择出 tr里的第二个td
                //td.product-name 不加空格指向的是td标签 加空格指向的是后代
                //$("td.product-name").css('background-color', 'blue');
                //下一行的代码将产品名称的那一列选择出来 这里不会跳转到跳转到此click函数绑定的 a 的 href
                //$("td.product-name").eq(0).css('background-color', 'blue');

                //下一行的代码将产品名称的那一列选择出来 这里会跳转到此click函数绑定的 a 的 href
                //$("td.product-name").eq(0).text().css('background-color', 'blue')

                //return false;// 在下一语句的前面 输出false 拦截删除_a标签跳转成功 未跳转
                //$("td.product-name").eq(0).text().css('background-color', 'blue');
                //return false; // 在上一句语句的后面 输出false 拦截删除_a标签跳转失败 跳转了
                //结论: a标签绑定的点击事件内部抛出错误时 后面的代码不会再执行 走a标签的href
                // 也有特殊的 如jquery/test/a标签跳转细节.html 中的id="#a_href"

                //下面的执行语句可以正常输出el表达式中的值！
                //alert($("td.product-name").eq(0).text());//北欧风格小桌子name 可以正常输出el表达式中的值！

                let b = window.confirm("确定要删除家居 " + $(this).parent().parent().eq(0).children().filter("input[name='furnName']").val() + " 吗？");
                if (!b){
                    //老韩解读：如果我们返回的false ,则表示放弃提交，页面就会停留在原页面
                    return false;// 可以让 a标签不进行跳转，停留在原页面
                }
                //代码走到这里说明真的要删除
                // 是否要删除的标签参数
                var $del_confirm = $(this).parent().parent().eq(0).children().filter("input[name='del_confirm']");

                //这里走删除的代码
                //后端也要删除
                //去写实现的方法 后端删除成功后 在进行action=list的页面展示
                // 将要删除行的信息 传递给后端 调用 del方法 action=del
                //alert("通过类选择器获取到的jquery对象的长度= " + $(".furn_id").length)    //弹出的都是id=1
                //alert($(".delete_a.furn_id").val())    //弹出的都是id=1 因为这里点击的不是通过类选择器选择到的元素 情况有区别
                // 点击的该行
                //alert("通过点击的该删除图标得到对应的furnId= "+$(this).parent().parent().eq(0).children().filter("input[name='furnId']").val());

                //将 下面的值传递给后端服务器
                //$(this).parent().parent().eq(0).children().filter("input[name='furnId']").val()
                //发送ajax请求 或者通过删除a标签 设置的href 将该值发送过去 /jiaju_mall/manage/furnServlet?action=del&id=

                // 语法： jQuery.getJSON(url,data,success(data,status,xhr))
                //老师说明
                //1. 如果你通过jquery发出的ajax请求是get 并且 返回的数据格式是json
                //2. 可以直接使用getJSON() 函数，就很简洁
                myajax = $.getJSON(
                    "/jiaju_mall/manage/furnServlet?action=del&id=" + $(this).parent().parent().eq(0).children().filter("input[name='furnId']").val(),
                    {
                        //这里我们直接给json, 为啥我要传日期, 为了浏览器缓存
                        date: new Date()
                    },
                    function (data, status, xhr) {
                        console.log("ajax getJSON请求成功");
                        // 接收返回来的参数 并处理
                        console.log("response= ", data);
                        console.log("status= ", status)
                        console.log("xhr= ", xhr)
                        console.log("this= ", this)
                        // data 是json对象 => 显示转成json的字符串
                        //$("#div1").text(JSON.stringify(data));
                        //对返回的结果进行处理
                        // if (response == ""){  // 当设置的dataType: "json" 时，总是为false 服务器端返回字符串"" 会直接进入error函数进不来success函数
                        // if (data.username == "") {
                        // data. 的确实是 返回的json数据里的属性 而不是本页面中的字段 id

                        if (data.id == -1) {
                            // $("#myres").val() = "该用户名可用"; //这样写是错的，应该写在()内
                            console.log("删除成功")
                            //$("input[name='del_confirm']").val("true");  //设置的是所有的行的 不行 应该设置只返回了结果的行的！
                            // 下面这行代码设置失败，原因是 这里的this 是ajax中的对象 解决方法： 声明在外面
                            $del_confirm.val("true");

                            //$(this).parent().parent().eq(0).children().filter("input[name='del_confirm']").val("true");
                            //alert($("input[name='del_confirm']").val());
                            console.log("ajax中设置的对应行的 删除参考值= "+$del_confirm.val());

                        } else {
                            // $("#myres").val() = "该用户名不可用"; //这样写是错的，应该写在()内
                            // $("#div1").text() = JSON.stringify(response);
                            console.log("删除失败")

                        }
                    }
                )

                //alert("furn_id= " + $(".furn_id").val());
                //$(this).parent().parent().eq(0)
                //前端页面删除
                //$(this).parent().parent().eq(0) //对应的行

                // 第一次删除有bug 值为空
               /* ajax函数中根据后端返回来的值进行 对是否要删除前端的行 判断的值=  <empty string> furnServlet:175:25
                    ajax getJSON请求成功 furnServlet:139:33
                    response=
                    Object { id: -1, name: "", maker: "", price: -1, sales: 0, stock: 0, imgPath: "assets/images/product-image/default.jpg" }
                    furnServlet:141:33
                    status=  success furnServlet:142:33
                    xhr=
                    Object { readyState: 4, getResponseHeader: getResponseHeader(e), getAllResponseHeaders: getAllResponseHeaders(), setRequestHeader: setRequestHeader(e, t), overrideMimeType: overrideMimeType(e), statusCode: statusCode(e), abort: abort(e), state: state(), always: always(), catch: catch(e)
                        , … }
                    furnServlet:143:33
                    删除成功*/

                //console.log("ajax函数中根据后端返回来的值进行 对是否要删除前端的行 判断的值= ",$(this).parent().parent().eq(0).children().filter("input[name='del_confirm']").val());
                //console.log("length值= ",$(this).parent().parent().eq(0).children().filter("input[name='del_confirm']").length); //1
                //console.log("length值= ",$("input[name='del_confirm']").length);//52
                //下面的语句是 选中的是删除a标签所在的行 设置的是否要删除前端页面行的判断条件，
                // 该条件的值根据ajax返回的内容 进行动态的设置的
                // 第一次删除 后端删除成功，前端删除失败 但是ajax删除成功，第一次删除js中获取到的是给input[name='del_confirm'设置的默认值<empty string> 在删除前端第一个也会失败 因为后端数据库已经删除了数据，但是前端会把第一行删除，因为之前的一次 已经将所有的input[name='del_confirm'] 默认值 换为了true
                //if($(this).parent().parent().eq(0).children().filter("input[name='del_confirm']").val()){
                console.log("在$.when(myajax).done(function () 函数外获取 删除的参考值= ",$del_confirm.val()); //<empty string> 导致出现的原因是ajax 为异步请求 导致的 可以通过设置ajax为同步解决
                /*
                jQuery.ajax([settings]) 的参数 async
                async
                类型：Boolean
                默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
                注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。*/


                // 要删除的行 声明在ajax函数执行完的函数外面
                var $del_tr = $(this).parent().parent().eq(0);
                //myajax请求完毕时执行
                $.when(myajax).done(function () {
                    //要执行的操作
                    console.log("执行完Ajax后 删除 if前值= ",$del_confirm.val());
                if ($del_confirm.val()){
                    // 这里删除失败 是因为 this对象变了 解决方法 提到外面
                    //$(this).parent().parent().eq(0).remove();
                    $del_tr.remove();

                }

                // 下面这种设置的值 有一条删除成功了 后面的都会删除成功，即使后端数据库中没有数据，也删除成功，有问题
                //if($("input[name='del_confirm']").val()){
                //    $(this).parent().parent().eq(0).remove();
                    //alert("前端删除")
                //}
                });
                return false;
                //return true;
            })

            // 下面的是老韩删除furn的实现 获取到家居名字 并弹出对话框 确认是否删除
              /*给a标签的href 属性增加 请求地址 点击一次就提交一次请求 并且通过jstl循环后
              * 所有的tr 中的 删除a标签 herf属性的请求地址 都是"manage/furnServlet?action=delete&id=￥{furn.id}"
              * 点击时 所有的a 都绑定了请求地址 进入到后端的删除方法中
              *
              *
              * */
            // 给老韩的删除a标签绑定点击事件 用于弹出确认对话框 确认是否真的删除
            $("a.deleteCss").click(function (){
                /*
                * $(this).parent().parent().find("td:eq(1)").text() 获取到要删除的家居的名字
                * $(this).parent().parent() 取到了删除a标签所在的tr 行元素
                * .find("td:eq(1)") 找该tr的子类 td 并且是第二个td
                * .text() 只取文本内容 过滤掉 html内容 因为 第二个td中还包含一个a标签 是html元素内容！
                * */
                //confirm 弹出一个确认窗口
                // 点击确定，返回true
                // 点击取消，返回false
                return confirm("你确认删除【" + $(this).parent().parent().find("td:eq(1)").text() + "】吗？")

            })

            //给老韩的修改 a标签绑定点击事件 用于回显要被修改的家居信息
            //$("a.updateCss").click(function (){
                // 该a 标签 向后端请求 对应数据库中的数据 然后再回显给furn_update.jsp


            //})

        })



    </script>

    <style>
        <%--填充分页导航条下方的空白位置，是导航条不紧挨着下方黑色区域--%>
        #fill_div{
            height: 10px;
        }
    </style>

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
<%--<jsp:forward page="furn_update.jsp"></jsp:forward>--%>
                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <form class="action-form" action="#">
                                    <input class="form-control" placeholder="Enter your search key" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="#">后台管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">


                            <%--自己写的--%>
                            <%--<a href="views/manage/furn_add.jsp">添加家居</a>--%>

                            <%--这里直接通过a标签将参数传递过去 --%>
                            <a href="views/manage/furn_add.jsp?pageNo=${requestScope.page.pageNo}">添加家居</a>


                            <%--将当前页的pageNo 和 pageSize 传递过去--%>
                            <%--<a href="views/manage/furn_add.jsp?pageNo=${requestScope.page.pageNo}&pageSize=3">添加家居</a>--%>
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
<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">家居后台管理</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                <%--<form action="furnServlet" id="form2">--%>

                <%--改为可以上传文件的form表单 --%>
                <%--<form action="furnServlet" id="form2" method="post" enctype="multipart/form-data">--%>
                    <input type="hidden" name="action" value="furns">
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
                            <%--下行代码是和显示所有的家居信息相关的，对应后端的furnServlet list()--%>
<%--                            <c:forEach items="${requestScope.furns}" var="furn">--%>
                            <%--下行代码是和 分页显示 家居信息相关的，对应后端的furnServlet page()--%>
<%--                            <c:forEach items="${requestScope.page.items}" var="furn">--%>
                            <%--从session对象中获取items--%>
<%--                            <c:forEach items="${sessionScope.page.items}" var="furn">--%>
                            <c:forEach items="${requestScope.page.items}" var="furn">

                            <tr>
                                    <%--用于 确认ajax删除 是否成功 --%>
                                <input type="hidden" name="del_confirm" value="">

                                <%--增加一个input 用于给js代码 传递furn对象--%>
                                <%--统一绑定事件 可以使用id属性 但是打出来id都一样 html语法报错 但是可以成功取到value11--%>
                            <input type="hidden" class="furn_id" name="furnId" value="${furn.id}">
                            <input type="hidden" class="furn_name" name="furnName" value="${furn.name}">

                            <%--<input type="hidden" class="furn_id" name="furnId" value="${furn.id}">--%>
                                <td class="product-thumbnail">

                                    <%--function prev2(event) {
                                    //获取展示图片的区域
                                    var img2 = document.getElementById("prevView");
                                    //获取文件对象
                                    var file2 = event.files[0];
                                    //获取文件阅读器： Js的一个类，直接使用即可
                                    var reader2 = new FileReader();
                                    reader2.readAsDataURL(file2);
                                    reader2.onload = function () {
                                    //给img的src设置图片url
                                    img2.setAttribute("src", this.result);
                                    }
                                    }--%>
                                    <%--家居图1: <img src="2.jpg" alt="" width="200" height="200" id="prevView">--%>
                                    <%--<input type="file" name="pic" id="" value="" onchange="prev(this)"/>--%>

                                        <%--这里的img 就是要展示的图片！ 类似于家具图1的作用--%>
                                    <a href="#"><img class="img-responsive ml-3" src="${furn.imgPath}"
                                                     alt="" id=""/></a>
                                        <%--<input type="file" name="pic" id="" value="" onchange="prev(this)"/>--%>
                                </td>
                                <td class="product-name"><a href="#">${furn.name}</a></td>
                                <%--<td class="product-name"><a href="#">name</a></td>--%>
                                <td class="product-name"><a href="#">${furn.maker}</a></td>
                                <td class="product-price-cart"><span class="amount">${furn.price}</span></td>
                                <td class="product-quantity">
                                        ${furn.sales}
                                </td>
                                <td class="product-quantity">
                                        ${furn.stock}
                                </td>
                                        <td class="product-remove">

                                        <%--点击update a标签 直接进入修改信息的回显页面 --%>
                                        <%--<a href="views/manage/furn_update.jsp" id="update" class="updateCss"><i class="icon-pencil">修改</i></a>--%>
                                        <%--点击update a标签 进入furnServlet 的showFurn方法 获取该 修改信息 并进行页面转发到回显页面furn_update.jsp --%>
                                        <%--    <a href="manage/furnServlet?action=showFurn&id=${furn.id}" id="update" class="updateCss"><i class="icon-pencil">修改</i></a>--%>

                                            <%--自己的正确的思路 使用session域传递数据
                                            在a标签中将对应要修改的行所在的page对象的相关信息一同发送给相应的servlet--%>
                                                <%--<a href="manage/furnServlet?action=showFurn&id=${furn.id}&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}" id="update" class="updateCss"><i class="icon-pencil">修改</i></a>--%>

                                            <%--走老韩的思路 依旧使用request域传递参数--%>
                                            <a href="manage/furnServlet?action=showFurn&id=${furn.id}&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}" id="update" class="updateCss"><i class="icon-pencil">修改</i></a>


                                    <%--自己写的删除Furn信息 所使用的删除a标签--%>
                                    <%--<a href="##w" id="delete" class="delete_a"><i class="icon-close">删除</i></a>--%>

                                    <%--老韩实现的 删除Furn信息 所使用的删除a标签--%>
                                    <%--要删除的家居 id 使用el表达式直接取出来就可以了 因为点击的此处的a标签
                                    和jstl 中循环的furn 是对应的 取出来就是对应行的家居id--%>


                                    <%--<a href="manage/furnServlet?action=delete&id=${furn.id}" id="delete" class="deleteCss"><i class="icon-close">删除</i></a>--%>

                                    <%--在a标签中将对应要删除的行所在的page对象的相关信息一同发送给相应的servlet--%>
                                    <%--<a href="manage/furnServlet?action=delete&id=${furn.id}&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}" id="delete" class="deleteCss"><i class="icon-close">删除</i></a>--%>

                                    <a href="manage/furnServlet?action=delete&id=${furn.id}&pageNo=${requestScope.page.pageNo}" id="delete" class="deleteCss"><i class="icon-close">删除</i></a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>

                <%--添加分页模块  默认显示第一页
                点击超链接 显示对应的页
                前端 给后端传递的数据 :
                是第几页 pageNo
                每页 显示数据条数：pageSize

                前端根据
                --%>
                <%--<div id="page"><a href="manage/furnServlet?action=page&pageNo=1&pageSize=3">第1页</a></div>
                <div id="page"><a href="manage/furnServlet?action=page&pageNo=2&pageSize=3">第2页</a></div>
                <div id="page"><a href="manage/furnServlet?action=page&pageNo=3&pageSize=3">第3页</a></div>
                <div id="total_page"><a href="#">共有${requestScope.page.pageTotalCount}页</a></div>--%>
            </div>
        </div>
<%--        <!--  Pagination Area Start 分页导航条 走自己写的逻辑的代码-->
        <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
            &lt;%&ndash;   <ul>
                   <li><a href="manage/furnServlet?action=firstPage&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">首页</a></li>
                   &lt;%&ndash;获取当前是第几页
                   同时已经将前端传给后端的当前页 的页码封装到了page对象的pageNo 属性中
                   只需要取出pageNo 就可以知道是第几页了
                   pageNo=${requestScope.page.pageNo} 如果没传值 即第一次时 后端默认给一个1 第二次开始就有值了
                   &ndash;%&gt;
                   <li><a href="manage/furnServlet?action=previousPage&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">上页</a></li>

                   <li><a class="active" href="manage/furnServlet?action=page&pageNo=3&pageSize=${requestScope.page.pageSize}">3</a></li>
                   <li><a href="manage/furnServlet?action=page&pageNo=4&pageSize=${requestScope.page.pageSize}">4</a></li>
                   <li><a href="manage/furnServlet?action=page&pageNo=5&pageSize=${requestScope.page.pageSize}">5</a></li>

                   <li><a href="manage/furnServlet?action=nextPage&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">下页</a></li>

                   <li><a href="manage/furnServlet?action=lastPage&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">末页</a></li>
                   <li><a>共${requestScope.page.pageTotalCount}页</a></li>
                   <li><a>共${requestScope.page.totalRow}记录</a></li>
               </ul>&ndash;%&gt;

            &lt;%&ndash;从session域对象中获取page对象相关数据&ndash;%&gt;
            <ul>
                <li><a href="manage/furnServlet?action=firstPage&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">首页</a></li>
                &lt;%&ndash;获取当前是第几页
                同时已经将前端传给后端的当前页 的页码封装到了page对象的pageNo 属性中
                只需要取出pageNo 就可以知道是第几页了
                pageNo=${requestScope.page.pageNo} 如果没传值 即第一次时 后端默认给一个1 第二次开始就有值了
                &ndash;%&gt;
                <li><a href="manage/furnServlet?action=previousPage&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">上页</a></li>

                <li><a class="active" href="manage/furnServlet?action=page&pageNo=3&pageSize=${sessionScope.page.pageSize}">3</a></li>
                <li><a href="manage/furnServlet?action=page&pageNo=4&pageSize=${sessionScope.page.pageSize}">4</a></li>
                <li><a href="manage/furnServlet?action=page&pageNo=5&pageSize=${sessionScope.page.pageSize}">5</a></li>

                <li><a href="manage/furnServlet?action=nextPage&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">下页</a></li>

                <li><a href="manage/furnServlet?action=lastPage&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">末页</a></li>
                <li><a href="#">共${sessionScope.page.pageTotalCount}页</a></li>
                <li><a>共${sessionScope.page.totalRow}记录</a></li>
            </ul>
        </div>
        &lt;%&ndash;<div id="fill_div"></div>&ndash;%&gt;
        <!--  Pagination Area End -->--%>


        <!--  Pagination Area Start 分页导航条 走老韩的逻辑的代码-->
        <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
            <%--从session域对象中获取page对象相关数据--%>
                <ul>
                <%--首页 指第一页--%>
                <li><a href="manage/furnServlet?action=page&pageNo=1&pageSize=${sessionScope.page.pageSize}">首页</a></li>



                <%--显示所有页数的页码--%>
                <%--优化：最多显示5条 显示方式 当前页前面2页 当前页 当前页后面2页
                控制在第3 条的位置固定不动 前面显示两条 后面显示两条
                --%>
             <%--       <c:choose>
                        <c:when test="${score > 90}">
                            <h1>成绩优秀</h1>
                        </c:when>
                        <c:when test="${score > 70}">
                            <h1>成绩一般</h1>
                        </c:when>
                        <c:when test="${score > 60}">
                            <h1>成绩及格</h1>
                        </c:when>
                        <c:otherwise>
                            <h1>${score}分-没及格，下次努力</h1>
                        </c:otherwise>
                    </c:choose>--%>

                    <c:set scope="request" var="begin" value="1"></c:set>
                    <c:set scope="request" var="end" value="${requestScope.page.pageTotalCount}"></c:set>
                    <%--spacing :间隔
                    定义一个间隔 用来记录[pageNo+2 - 总页码] 的间隔数
                    --%>
                    <c:set scope="request" var="spacing" value="${requestScope.page.pageNo + 2 - sessionScope.page.pageTotalCount}"></c:set>

                    <c:choose>
                        <c:when test="${requestScope.page.pageTotalCount <= 5}">
                            <%--
                            总页码小于等于5
                            全部显示出来--%>
                            <c:forEach begin="${begin}" end="${end}" var="i">
                                <c:if test="${requestScope.page.pageNo == i}">
                                    <%--从begin=1 开始循环 循环到end=总页数
                                        因为在每一页都有显示其他页的页码
                                        并且当在前端页面显示出来的页码数等于pageNo时高亮显示
                                    --%>
                                    <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>

                                <c:if test="${requestScope.page.pageNo != i}">
                                    <%--<li><a href="manage/furnServlet?action=page&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>--%>
                                    <li><a href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:when>

                        <c:when test="${requestScope.page.pageNo - 2 < 1 && requestScope.page.pageTotalCount > 5}">
<%--                        <c:when test="${sessionScope.page.pageNo < 3 && sessionScope.page.pageTotalCount > 5}">--%>
                            <%--
                            总页码大于5 要显示第1 和 第2页  使用pageNo - 2 时会出现负数和0 因此 应该单独写一个条件判断
                            当pageNo < 3 并且总页码大于5时 显示1-5的页码
                            全部显示出来--%>
                            <c:forEach begin="${begin}" end="5" var="i">
                                <c:if test="${requestScope.page.pageNo == i}">
                                    <%--从begin=1 开始循环 循环到end=总页数
                                        因为在每一页都有显示其他页的页码
                                        并且当在前端页面显示出来的页码数等于pageNo时高亮显示
                                    --%>
                                    <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>

                                <c:if test="${requestScope.page.pageNo != i}">
                                    <%--<li><a href="manage/furnServlet?action=page&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>--%>
                                    <li><a href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:when>

                        <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2 <= requestScope.page.pageTotalCount}">
                            <%--显示出当前页前面两页 当前页 当前页后面两页 总共5页
                             当前页码要大于等于3 sessionScope.page.pageNo >= 3
                             否则下面的sessionScope.page.pageNo - 2 会为不存在的页码
                             此条件可以控制 让此规则从页码为3开始生效

                            此时 begin=pageNo-2 , end=pageNo+2
                            sessionScope.page.pageNo + 2 <= sessionScope.page.pageTotalCount
                            因为上面的条件是当前页加2 小于等于总页数

                            所以 :当前页数加2 的最大值取到 和总页数相等
                            不会超过总页数 即不会显示出不存在的页码
                            --%>
                            <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                                <c:if test="${requestScope.page.pageNo == i}">
                                    <%--从begin=sessionScope.page.pageNo - 2 开始循环 循环到end=sessionScope.page.pageNo + 2
                                        因为在每一页都有显示其他页的页码
                                        并且当在前端页面显示出来的页码数等于pageNo时高亮显示
                                    --%>
                                    <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>

                                <c:if test="${requestScope.page.pageNo != i}">
                                    <%--<li><a href="manage/furnServlet?action=page&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>--%>
                                    <li><a href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:when>

                        <c:when test="${requestScope.page.pageNo - 2 >= 1 && requestScope.page.pageTotalCount > 5 && requestScope.page.pageNo + 2 > requestScope.page.pageTotalCount}">
                            <%--当当前页码大于等于3 并且 总页数大于5 并且
                             当前页码要大于3 sessionScope.page.pageNo > 3
                             否则下面的sessionScope.page.pageNo - 2 会为不存在的页码
                             此条件可以控制 让此规则从页码为3开始生效

                             当前页的页码+2 大于 总页数时
                            需要改变页码显示的方式
                            begin=pageNo-2-spacing , end=pageNo+2-spacing
                            spacing=(pageNo+2) - 总页数
                            即两边同时减去 pageNo+2超过总页码的值spacing
                            --%>
                            <%--显示出当前页前面两页 当前页 当前页后面两页 总共5页
                           此时 begin=pageNo-2 , end=pageNo+2
                           sessionScope.page.pageNo + 2 <= sessionScope.page.pageTotalCount
                           因为上面的条件是当前页加2 小于等于总页数 所以 :当前页数加2 的最大值取到 和总页数相等
                           不会超过总页数 即不会显示出不存在的页码
                           --%>
                            <c:forEach begin="${requestScope.page.pageNo - 2 - spacing}" end="${requestScope.page.pageNo + 2 - spacing}" var="i">
                                <c:if test="${requestScope.page.pageNo == i}">
                                    <%--从begin=sessionScope.page.pageNo - 2 - spacing开始循环 循环到end=sessionScope.page.pageNo + 2 - spacing
                                        因为在每一页都有显示其他页的页码
                                        并且当在前端页面显示出来的页码数等于pageNo时高亮显示
                                        这里一个加2 一个减2 取得是五个页码的 因为还有当前页包含在其中
                                    --%>
                                    <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>

                                <c:if test="${requestScope.page.pageNo != i}">
                                    <%--<li><a href="manage/furnServlet?action=page&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>--%>
                                    <li><a href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:when>
                    </c:choose>

                    <%--老韩的分页 ====================================--%>
                    <%--    显示所有的分页数， 先容易，再困难
                   老师思路: 先确定开始页数 begin 第1页
                           再确定结束页数 end 第pageTotalCount页
                   学生困惑：如果页数很多，怎么办? => 算法最多显示5页[这个规则可以由程序员决定.]
                           希望，小伙伴自己先想一想...=> 后面

                   老师分析
                   1. 如果总页数<=5, 就全部显示
                   2. 如果总页数>5, 按照如下规则显示(这个规则是程序员/业务来确定):
                   2.1 如果当前页是前3页, 就显示1-5
                   2.2 如果当前页是后3页, 就显示最后5页
                   2.3 如果当前页是中间页, 就显示 当前页前2页, 当前页 , 当前页后两页

                   这里的关键就是要根据不同的情况来初始化begin, end
               --%>
<%--                    <c:choose>--%>
<%--                        &lt;%&ndash;如果总页数<=5, 就全部显示&ndash;%&gt;--%>
<%--                        <c:when test="${requestScope.page.pageTotalCount <=5 }">--%>
<%--                            <c:set var="begin" value="1"/>--%>
<%--                            <c:set var="begin" value="${requestScope.page.pageTotalCount}"/>--%>
<%--                        </c:when>--%>
<%--                        &lt;%&ndash;如果总页数>5&ndash;%&gt;--%>
<%--                        <c:when test="${requestScope.page.pageTotalCount > 5}">--%>
<%--                            <c:choose>--%>
<%--                                &lt;%&ndash;如果当前页是前3页, 就显示1-5&ndash;%&gt;--%>
<%--                                <c:when test="${requestScope.page.pageNo <= 3}">--%>
<%--                                    <c:set var="begin" value="1"/>--%>
<%--                                    <c:set var="end" value="5"/>--%>
<%--                                </c:when>--%>
<%--                                &lt;%&ndash;如果当前页是后3页, 就显示最后5页&ndash;%&gt;--%>
<%--                                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotalCount - 3}">--%>
<%--                                    <c:set var="begin" value="${requestScope.page.pageTotalCount - 4}"/>--%>
<%--                                    <c:set var="end" value="${requestScope.page.pageTotalCount}"/>--%>
<%--                                </c:when>--%>
<%--                                &lt;%&ndash;如果当前页是中间页, 就显示 当前页前2页, 当前页 , 当前页后两页&ndash;%&gt;--%>
<%--                                <c:otherwise>--%>
<%--                                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>--%>
<%--                                    <c:set var="end" value="${requestScope.page.pageNo + 2}"/>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </c:when>--%>
<%--                    </c:choose>--%>

                    <%-- 将开始和结束的页码设置为变量 在前面c:set 中设置后 在下面统一进行遍历 显示页码的处理--%>
<%--                    <c:forEach begin="${begin}" end="${end}" var="i">--%>
<%--                        &lt;%&ndash;如果i是当前页, 就使用class="active" 修饰&ndash;%&gt;--%>
<%--                        <c:if test="${i == requestScope.page.pageNo}">--%>
<%--                            <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}">${i}</a></li>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${i != requestScope.page.pageNo}">--%>
<%--                            <li><a href="manage/furnServlet?action=page&pageNo=${i}">${i}</a></li>--%>
<%--                        </c:if>--%>

<%--                    </c:forEach>--%>

                    <%--完成上一页 下一页--%>
                    <%--上一页
                    当前页的页码要大于1 才有上一页 如果当前页等于1 上一页的li a 不显示 即不会出现在页面上
                    --%>
                    <c:if test="${requestScope.page.pageNo > 1}">
                        <li><a href="manage/furnServlet?action=page&pageNo=${requestScope.page.pageNo - 1}&pageSize=${requestScope.page.pageSize}">上页</a></li>
                    </c:if>
                    <%--下一页
                    当前页的页码要小于总页数 才有下一页
                    --%>
                    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
                        <li><a href="manage/furnServlet?action=page&pageNo=${requestScope.page.pageNo + 1}&pageSize=${requestScope.page.pageSize}">下页</a></li>
                    </c:if>

                <%--获取当前是第几页
                同时已经将前端传给后端的当前页 的页码封装到了page对象的pageNo 属性中
                只需要取出pageNo 就可以知道是第几页了
                pageNo=${requestScope.page.pageNo} 如果没传值 即第一次时 后端默认给一个1 第二次开始就有值了
                --%>

                <li><a href="manage/furnServlet?action=page&pageNo=${requestScope.page.pageTotalCount}&pageSize=${requestScope.page.pageSize}">末页</a></li>
                <li><a href="#">共${requestScope.page.pageTotalCount}页</a></li>
                <li><a>共${requestScope.page.totalRow}记录</a></li>
            </ul>
        </div>
        <%--<div id="fill_div"></div>--%>
        <!--  Pagination Area End -->
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
                            <%--img src="#" 因为写了base标签 所以参考base标签后，
                            会去请求当前页url http://localhost:8080/jiaju_mall/# --%>
                            <%--<img src="#" alt="">--%>
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