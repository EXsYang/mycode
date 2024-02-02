<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/17
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%-- 在用Maven创建的web项目中 isELIgnored="false" 默认是true
  el表达式不能生效
  但是这里是普通的项目 默认就生效
   究其原因是和web.xml 的头文件有关


   原因：
   jsp中为什么有时候需要加isELIgnored=“false”，有时候不加？
在IDEA写jsp的时候一直有个疑问

在jsp的头文件中有时候是下面这种写法

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
1
有时候又是这种写法：

<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
1
他们两个的差别就是一个中有isELIgnored="false"，一个中没有这个。很是苦恼，到底什么时候加这个，什么时候不加这个。他们都和EL表达式有关，有时候加上isELIgnored="false"EL表达式就可以用，但是有时候没加也可以用下面来探究一下。

这个就要从web.xml的头文件说起。
如果不是maven项目的话，头文件一般来说是这样的

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

如果是maven项目的话web.xml的头文件是下面这样的：

<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

这些头文件主要是一些协议，maven的默认的是2.3版本的（据说是为了扩展性），而2.5版本以上才支持EL表达式。所以只需要把web.xml换为以下头文件，就不需要加isELIgnored="false"

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">


   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
