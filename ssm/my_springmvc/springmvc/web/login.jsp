<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/9/24
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>登录页面</h3>

<%--这里的路径 涉及到 Javaweb 的 web路径专题
1.action="login" 表示的url 是 http://localhost:8080/springmvc/login
2.action="/login" 表示的url 是 http://localhost:8080/login
3.找到login 方法 再跳转到login_ok.jsp页面
--%>

<%--form 表单不写 method 默认是get--%>
<form action="login">
<%--<form action="login" method="post">--%>
    u:<input name="username" type="text"> <br/>
    p:<input name="password" type="password"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>