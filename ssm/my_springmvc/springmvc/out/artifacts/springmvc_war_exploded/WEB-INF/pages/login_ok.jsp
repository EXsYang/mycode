<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/9/24
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login_ok页面-登录成功</title>
</head>
<body>
<h1>恭喜 登录成功！！</h1>
欢迎您 ${requestScope.user.username} <br/>
用户信息：${requestScope.user}
</body>
</html>
