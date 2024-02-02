<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/11
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>四个域对象，其他页面获取数据情况</title>
</head>
<body>
<h1>四个域对象，在scope2页面获取数据情况</h1>


pageContext-k1: <%=pageContext.getAttribute("k1")%><br/>

request-k1: <%=request.getAttribute("k1")%><br/>
session-k1: <%=session.getAttribute("k1")%><br/>
application-k1: <%=application.getAttribute("k1")%><br/>
</body>
</html>
