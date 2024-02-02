<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/18
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>el的运算符</title>
</head>
<body>
<h1>el的运算符</h1>
<%
    request.setAttribute("num1", 90);
    request.setAttribute("num2", 30);
%>

num1 + num2 = ${num1 + num2} <br/>
num1 > num2 = ${num1 > num2}<br/>
<%--上面的表达式在浏览器显示为下面两行代码--%>
num1 + num2 = 120<br/>
num1 > num2 = true<br/><br/><br/>


num1 = ${num1}<br/> 90
num2 = ${num2}<br/> 30
num1 = num2 一个等号相当于赋值符号 结果为：${num1 = num2} 输出的是num2的值<br/>
num1 = ${num1}<br/>
num1 == num2 = ${num1 == num2} <br/>
num1 == num2 = ${num1 eq num2}也可以使用 eq代替==<br/>
num1 != num2 = ${num1 != num2} <br/>
num1 != num2 = ${num1 ne num2} 也可以使用 ne代替!= <br/>


</body>
</html>
