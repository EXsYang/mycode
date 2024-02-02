<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/10
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp九大内置对象</title>
</head>
<body>
<h1>jsp九大内置对象</h1>
<p>1. out 向客户端输出数据，out.println("");</p>
<p>2. request 客户端的 http 请求</p>
<p>3. response 响应对象</p>

<p>4. session 会话对象</p>
<p>5. application 对应 ServletContext</p>
<p>   6. pageContext jsp 页面的上下文，是一个域对象，可以 setAttribue(),作用范围只是本页面</p>
<%--exception对象表示jsp引擎在执行代码时抛出的异常
如果想要使用exception对象，那么需要配置编译指令的isErrorPage属性为true,即在页面指令中设置:
<%@page isErrorPage="true"%>  --%>
<p>7. exception 异常对象 , getMessage() 忽略，用的很少 </p>
<p>   8. page 代表 jsp 这个实例本身 相当于this</p>
<p>9. config 对应 ServletConfig</p>

<%
    //老韩梳理jsp的内置对象
    //out 类型是 JspWriter 父类就是 Writer.
    out.println("jsp out");
    //request是HttpServletRequest
    request.getParameter("age");
    //response就是 HttpServletResponse
    //response.sendRedirect("http://www.baidu.com");
    //session 就是 HttpSession
    session.setAttribute("job", "PHP工程师");
    //application类型就是ServletContext
    application.setAttribute("name", "老韩老师");
    //pageContext 可以存放数据(属性), 但是该数据只能在本页面使用
    pageContext.setAttribute("age", 100);
    //exception 异常对象 使用比较少
    //page 内置对象，类似 this
    out.println("page=" + page);
    //config 内置对象的类型就是ServletConfig
    String username = config.getInitParameter("username");
    String pwd = config.getInitParameter("pwd");


%>
<br/>
pageContextAge: <%=pageContext.getAttribute("age")%><br/>



configUsername: <%=username%><br/>
configPwd: <%=pwd%><br/>

</body>
</html>
