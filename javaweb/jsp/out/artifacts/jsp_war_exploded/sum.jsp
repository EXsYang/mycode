<%@ page import="java.io.PrintWriter" %>
<%@ page import="org.apache.jasper.runtime.HttpJspBase" %><%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  jsp的模板如何定制，一会再说明
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp的简单的求和计算器</title>
   <%-- 当导入 servlet-api.jar	jsp-api.jar
   (jasper.jar 将jsp翻译成.java文件后 查看继承的类  查看相关的类图 相关的父类在此包下)
    后就可以使用 <%!%> <%%> <%=%> 还有jsp标签了	<jsp:forward page="/bb.jsp"></jsp:forward>--%>
</head>
<body>
<h1>jsp的简单的求和计算器</h1>
<%
    //老韩解读
    //1. 在jsp的 该标签中, 可以写java代码

    int i = 10;
    int j = 20;
    int res = i + j;

    //2. jsp 中内置对象，可以直接使用, 比如 out
    //   老韩小技巧：先死后活 ctrl + alt + l
    out.println(i + " + " + j + " = " + res);
    //3. 老师说明，如果要看HttpJspBase类的关系, 需要引入一个jasper.jar包， alt+enter



    //在java片段中,仍然是java的注释
    String email = "xx@qq.com";
    /*
        多行注释
        public final class sum_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {
     */
    //HttpJspBase //查看类图关系 public abstract class HttpJspBase extends HttpServlet implements HttpJspPage {

    /* sum_jsp.java文件路径：
    启动Tomcat时，在最上面的CATALINA_BASE(基础)目录
    Using CATALINA_BASE:   "C:\Users\yangd\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_jsp"

         C:\Users\yangd\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\
         Unnamed_jsp\work\Catalina\localhost\jsp\org\apache\jsp
     */
%>
<%--email: <%=email%>--%>
<!--html注释 -->

</body>
</html>
