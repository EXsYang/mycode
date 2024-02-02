<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/18
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el empty的运算</title>
</head>
<body>
<h1>el empty的运算</h1>
<%
    request.setAttribute("k1",null);
    request.setAttribute("k2","");
    request.setAttribute("k3",new Object[]{});
    request.setAttribute("k4",new ArrayList<>());
    request.setAttribute("k5",new HashMap<>());
    request.setAttribute("k8","name");
    // 没有设置k6!
     request.setAttribute("score",78);

%>

k1是否为空-${empty k1} <br/>
k2是否为空-${empty k2} <br/>
k3是否为空-${empty k3} <br/>
k4是否为空-${empty k4} <br/>
k5是否为空-${empty k5} <br/><br/>

k6是否为空-${empty k6} <br/>
k7是否为空-${empty k7} <br/>
k8是否为空-${empty k8} <br/>
是否及格-${score > 60 ? "及格了" : "没及格"}
</body>
</html>
