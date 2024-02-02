<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/18
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示妖怪信息</title>
</head>
<body>
<h1>显示妖怪信息</h1>
<table border="1px"bgcolor="#ffc0cb">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>job</td>
        <td>sal</td>
    </tr>
    <c:forEach items="${requestScope.monsters}" var="monster">
        <c:if test="${monster.sal > 1000}">
            <tr>
                <td>${monster.id}</td>
                <td>${monster.name}</td>
                <td>${monster.job}</td>
                <td>${monster.sal}</td>
            </tr>
        </c:if>

    </c:forEach>


</table>



</body>
</html>
