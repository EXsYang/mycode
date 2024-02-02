<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/10/5
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"  %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<h1>登录成功</h1>
欢迎您 ${requestScope.mName} <br/>
${sessionScope.mName}
</body>
</html>
