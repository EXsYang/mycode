<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/10/14
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试自定义拦截器</title>
</head>
<body>
<h1>测试自定义拦截器</h1>
<a href="<%=request.getContextPath()%>/hi">测试自定义拦截器hi</a><br><br>
<a href="<%=request.getContextPath()%>/hello">测试自定义拦截器hello</a><br><br>
<a href="<%=request.getContextPath()%>/ok">测试自定义拦截器ok</a><br><br>
</body>
</html>
