<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/20
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理后台登录</title>
</head>
<body>
<h1>管理后台登录</h1>
<%--<%--%>
<%--    //System.out.println("login_request=" + request);--%>
<%--%>--%>
<form action="<%=request.getContextPath() %>/loginCheckServlet" method="post">
    u：<input type="text" name="username"/> <br/><br/>
    p：<input type="password" name="password"/> <br/><br/>
    <input type="submit" value="用户登录"/></form>
</body>
</html>
