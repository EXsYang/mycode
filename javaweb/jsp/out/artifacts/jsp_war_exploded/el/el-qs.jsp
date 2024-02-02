<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/17
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式快速入门</title>

</head>
<body>
<h1>el表达式快速入门</h1>

<%
    request.setAttribute("name","yangda");
%>
<%--老韩解读
    EL 表达式是为了替换 jsp 中的表达式脚本，JSTL 是为了替换代码脚本。!!!
    表达式脚本: <%=%>
    代码脚本:   <%%>

    1. 如果name是 null, request.getAttribute() 返回的是"null"字符串
    2. 如果name是 null, ${name}, 返回的""


--%>
<h1>jsp表达式脚本</h1>
名字= <%=request.getAttribute("name") == null ? "" : request.getAttribute("name")%> <br/>
<h1>el表达式</h1>
名字= ${name}<br/>



</body>
</html>
