<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/10/5
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>

</head>
<body>

<h1>登录页面</h1>
<%--<form action="/monster/login">--%>
    <%-- form表单使用post 方式提交表单 后端如果没有使用  request.setCharacterEncoding("utf-8");
     进行处理 得到的中文是乱码 ,但是如果使用的是get 方式 提交表单 后端就不会出现中文乱码问题
     结论 form表单 如果用的是post方式提交表单 服务器端需要进行中文乱码的处理
     --%>
<%--<form action="/monster/login" method="post">--%>
<%--<form action="/goods/list" method="post">--%>
<form action="/goods/json" method="post">

    <%--妖怪名： <input type="text" name="mNamex"> <br/>--%>
    <%--货物名： <input type="text" name="name"> <br/>--%>
    货物名： <input type="text" name="goodsName"> <br/>
    <input type="submit" value="提交登录" >

</form>

</body>
</html>
