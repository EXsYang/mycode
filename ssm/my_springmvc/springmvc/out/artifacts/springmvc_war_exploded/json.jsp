<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/10/12
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json 提交</title>
    <!-- 引入jquery -->
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>

    <!-- 编写jquery代码和ajax请求 -->
     <script>

        $(function (){ //页面加载完成后 执行此函数
            //给id="getJson"绑定点击事件
            $("#getJson").click(function (){
                //点击后 发出Ajax请求
                var url = this.href;
                var args = {"time": new Date};//这是老师要发送数据,为了防止页面缓存
                $.post(
                    //"/springmvc/json/dog",  //这种不灵活
                    //{
                    //    date:new Date()
                    //},

                    url,
                    args,
                    function (data,status,xhr){ //data 就是返回的数据,是json格式=>如果是多个json数据，可以遍历
                        console.log("ajax返回的数据data= ",data);
                        console.log("data.name= ",data.name);
                        console.log(" data.address= ",data.address);
                    },
                    "json"
                )
                //取消a标签点击后跳转的行为 不使用href默认机制
                return false;
            })


            //绑定按钮点击事件, 提交 json 数据
            //springmvc 可以在後台將 json 轉成對象
            $("button[name='butt1']").click(function () {
                //目标：将userName 和 age 封装成json字符串，发送给目标方法
                var url = "/springmvc/json/save2";

                var userName = $("#userName").val();
                var age = $("#age").val();
                //var args = {"username":userName,"age":age};
                //发送到json数据需要和后端要封装的 JavaBean中属性名保持一致 否则为null
                var args = {"userName":userName,"age":age};
                //将json对象转成json字符串
                var jsonStringArgs = JSON.stringify(args);
                alert("jsonStringArgs="+jsonStringArgs);
                //发送ajax请求 注意这种形式 需要在一对大括号中填写参数
                $.ajax({
                    type:"post",
                    //指定参数url
                    url:url,
                    data:jsonStringArgs,
                    success:function (data){

                        console.log("ajax返回的数据data= ",data);
                    },
                    //dataType:"json",// "json" 要求服务器返回的数据格式是json
                    //下面这句话 表示 我们提交的ajax请求 它的数据data:args 是一个json格式数据
                    //指定发送数据时的编码和格式
                    contentType:"application/json;charset=utf-8"

                })




                //alert("userName=" + userName);
                //alert("age=" + age);

                // 发送ajax请求




            })

        })

    </script>


</head>
<body>
<h1>请求一个 json 数据</h1>
<%--老师处理
1.当用户点击超链接时，我们发出一个ajax请求
2. 接收到请求后，我们查看这个数据
3. 使用老韩前面讲过的jquery发出ajax请求知识
--%>
<a href="<%=request.getContextPath()%>/json/dog" id="getJson">点击获取 json 数据</a>

<h1>发出一个 json 数据</h1>
u:<input id="userName" type="text"><br/>
a:<input id="age" type="text"><br/>
<button name="butt1">添加用户</button>
<h1>下载文件的测试</h1>
<a href="<%=request.getContextPath()%>/downFile">点击下载文件</a>
</body>




</html>
