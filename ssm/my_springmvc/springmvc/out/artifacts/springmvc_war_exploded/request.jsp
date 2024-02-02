<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/9/25
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买商品</title>
</head>
<body>
<h1>购买商品</h1>
<%--1. action="user/buy" 对应 url http://localhost:8080/工程路径/user/buy--%>
<%--<form action="user/buy" method="get">--%>
<form action="user/buy" method="post">
    购买人:<input type="text" name="username"><br>
    够买量:<input type="text" name="nums"><br>
    <input type="submit" value="购买">
</form>

<hr><h1>演示params的使用</h1>
<a href="user/find?bookId=100">查询书籍</a>

<hr><h1>演示Ant风格的请求资源方式 </h1>
<a href="user/message/indx">发送消息1</a><br>
<a href="user/message/xxxxxxxxxaa">发送消息2</a><br>
<a href="user/message/aa/bb/cc">发送消息3</a><br>

<hr><h1>占位符的演示</h1>
<%--user/reg 这一节不能乱写,否则不能匹配 UserHandler 中的 /user/reg
/kristina/300 是参数 可以自定义书写 因为这里是传递的参数
--%>
<a href="user/reg/kristina/300">占位符的演示</a>


<br/>
<form action="computer/info" method="post">
    品牌:<input type="text" name="brand"/><br/>
    价格:<input type="text" name="price"/><br/>
    数量:<input type="text" name="count"/><br/>
    <input type="submit" value="提交">
</form>



</body>
</html>
