<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/18
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el 四个常用的隐藏对象(域对象)</title>
</head>
<body>
<h1>el 四个常用的隐藏对象(域对象)</h1>

<%
    request.setAttribute("k1","request-k1数据");
    pageContext.setAttribute("k1","pageContext-k1数据");
    session.setAttribute("k1","session-k1数据");
    application.setAttribute("k1","application-k1数据");
%>
<%

//request.setAttribute("k1", "request-k1的值");
//session.setAttribute("k1", "session-k1的值");
//application.setAttribute("k1", "application-k1的值");
//pageContext.setAttribute("k1", "pageContext-k1的值~");
%>
<%--老师多说一句
1. 如果${requestScope.score} 那么就明确的指定从request域对象取出数据
2. 如果${score}, 这是就按照从小到大的域范围去获取 pageContext->request->session->application
3. 一会老韩给小伙伴验证一把.
--%>
k1=${k1}

<h1>jsp脚本方式获取</h1>
    request域中的k1= <%=request.getAttribute("k1")%>
<h1>el方式来获取域对象的数据</h1>
request域中的k1=${requestScope.k1}<br/>
pageContext域中的k1=${pageScope.k1}<br/>
session域中的k1=${sessionScope.k1}<br/>
application域中的k1=${applicationScope.k1}<br/>


</body>
</html>
