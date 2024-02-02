<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/18
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>c:if</title>
</head>
<body>
<%--c:if 标签中 不要填scope属性 填了会报错！！！
HTTP Status 500 - <h3>Validation error messages from TagLibraryValidator for c in /jstl/c_if.jsp</h3><p>17: Illegal scope attribute without var in "c:if" tag.</p>
--%>
<%--<c:set scope="request" var="num1" value="100"></c:set>--%>
<c:set scope="request" var="num1" value="101"></c:set>
<%--<c:set scope="request" var="num1" value="200"></c:set>--%>
<c:set scope="request" var="num2" value="20"></c:set>
<h1>${num1} - ${num2}</h1>
num1=${requestScope.num1} num2=${requestScope.num2}<br>

<%--正确的写法：不加scope属性！--%>
<c:if test="true">
    <h1>if2 ${num1} > ${num2}</h1>
</c:if>


<%--错误的写法：加了scope属性！会报错--%>
<%--<c:if scope="page" test="${requestScope.num1 > num2}">--%>
<%--    <h1>${num1} > ${num2}</h1>--%>
<%--</c:if>--%>



<%--<c:if scope="application" test="true">--%>
<%--    <h1>if2 ${num1} > ${num2}</h1>--%>
<%--</c:if>--%>


<%--<c:if scope="request" test="true">--%>
<%--    <h1>if2 ${num1} > ${num2}</h1>--%>
<%--</c:if>--%>







</body>
</html>
