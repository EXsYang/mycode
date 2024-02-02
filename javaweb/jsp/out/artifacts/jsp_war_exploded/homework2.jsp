<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/11
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示计算结果</title>
</head>
<body>
<h1>计算结果</h1>
<%
    String num1 = request.getParameter("num1");
    String num2 = request.getParameter("num2");

    String operator = request.getParameter("operator");
    int intNum1 = 0;
    int intNum2 = 0;
    if (!"".equals(num1)) {
        intNum1 = Integer.parseInt(num1);
    }
    if (!"".equals(num2)) {
        intNum2 = Integer.parseInt(num2);
    }
    double sum;
    String oper = "@";
    if ("add".equals(operator)) {
        sum = intNum1 + intNum2;
        oper = "+";
    } else if ("subtract".equals(operator)) {
        sum = intNum1 - intNum2;
        oper = "-";
    } else if ("multiply".equals(operator)) {
        sum = intNum1 * intNum2;
        oper = "*";
    } else {
        sum = intNum1 / intNum2;
        oper = "/";
    }

%>
<%=intNum1%> <%=oper%> <%=intNum2%> = <%=sum%>  <br/><br/>

<a href="<%=request.getContextPath()%>/homework.jsp">返回再来玩一次</a>
</body>
</html>
