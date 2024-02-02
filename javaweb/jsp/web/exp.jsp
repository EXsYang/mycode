<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表达式脚本的使用</title>
    <%--
    表达式:只要有值返回都可以称为表达式，甚至可以是一个常量
    格式：<%=表达式%>
    作用：在jsp页面上输出数据
    表达式屁股后面不要带分号; 即表达式不可以分号结束！
    --%>
</head>
<body>
<h1>个人信息</h1>
<%
    String name = "杨达";
    int age = 44;

%>
<%--http://localhost:8080/jsp/exp.jsp?age=33&email=3232@qq.com--%>
用户名：<%=name%> <br/>
工作是：<%="java工程师"%><br/>
得到的年龄：<%=request.getParameter("age")%><br/><%--33--%>
得到的年龄：<%=age%><br/><%--44--%>
email: <%=request.getParameter("email")%>

</body>
</html>
