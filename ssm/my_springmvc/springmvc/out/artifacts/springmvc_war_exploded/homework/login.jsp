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

<%-- url 是 http://localhost:8080/springmvc/homework/login.jsp
--%>

<%--form 表单不写 method 默认是get--%>
<%--<form action="login">--%>
<form action="../user/login">
<%--<form action="login" method="post">--%>
    u:<input name="username" type="text"> <br/>
    p:<input name="pwd" type="password"><br/>
    <input type="submit" value="登录">
</form>

<%--老韩解读
1. <%=request.getContextPath()%>/user/login => /springmvc/user/login
2. /springmvc/user/login => http://localhost:8080/springmvc/user/login
3. 如果小伙伴前面学习的非常扎实，理解没有问题, 如果有点懵, 就回去复习javaweb->web工程路径
--%>
<form action="<%=request.getContextPath()%>/user/login2" method="post">
    u:<input name="username" type="text"> <br/>
    p:<input name="pwd" type="password"><br/>
    <input type="submit" value="登录">
</form>
</body>


</html>