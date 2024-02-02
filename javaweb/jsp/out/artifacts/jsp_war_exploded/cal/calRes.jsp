<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/17
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>计算结果</title>
</head>
<body>
<h1>计算结果</h1>
<%--从域对象动态获取结果并显示--%>
<%=request.getAttribute("res")%><br/>

<a href="<%=request.getContextPath()%>/cal/calUI.jsp">返回再玩一次</a>
</body>
</html>
