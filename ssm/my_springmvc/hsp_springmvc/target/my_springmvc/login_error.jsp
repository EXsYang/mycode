<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/10/5
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>登录失败页面</title>
</head>
<body>
<h1>登录失败</h1>
sorry... ${requestScope.mName} 登录失败
<br/>
${sessionScope.mName}
</body>
</html>
